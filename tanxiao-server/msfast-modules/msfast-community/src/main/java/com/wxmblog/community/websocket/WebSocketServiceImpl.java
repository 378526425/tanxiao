package com.wxmblog.community.websocket;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;

import com.wxmblog.base.common.service.RedisService;
import com.wxmblog.base.common.utils.NumberUtils;
import com.wxmblog.base.websocket.netty.ChannelMap;
import com.wxmblog.base.websocket.service.IWebSocketService;
import com.wxmblog.base.websocket.utils.ChannelUtil;
import com.wxmblog.community.common.constant.Constants;
import com.wxmblog.community.common.enums.MessageTypeEnum;
import com.wxmblog.community.common.type.MatchingType;
import com.wxmblog.community.common.type.MessageInfo;
import com.wxmblog.community.service.AsyncService;
import com.wxmblog.community.service.FrUserService;
import io.netty.channel.Channel;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-10-12 17:28
 **/

@Service
public class WebSocketServiceImpl implements IWebSocketService {

    @Resource
    private RedisService redisService;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    FrUserService frUserService;

    @Autowired
    ChannelUtil channelUtil;

    @Autowired
    AsyncService asyncService;

    @Override
    public void read(Channel channel, String text) {
        MessageInfo message = JSON.parseObject(text, MessageInfo.class);
        if (MessageTypeEnum.MATCHING.equals(message.getMessageType())) {
            if (StringUtils.isNotBlank(message.getInfo())) {
                String info = message.getInfo().replaceAll("'", "\"");
                MatchingType matchingType = JSON.parseObject(info, MatchingType.class);
                Channel connect = ChannelMap.get(matchingType.getUserId());
                if (connect == null) {
                    channelUtil.sendText(channel, "未绑定连接");
                } else {

                    RLock lock = redissonClient.getLock(Constants.MATCHING_LOCK);
                    try {
                        lock.lock();
                        //开始匹配
                        matching(matchingType);
                    } finally {
                        lock.unlock();
                    }

                }
            }
        }
    }

    @Override
    public void close(Channel channel) {
        Map.Entry<Integer, Channel> map = ChannelMap.getManager().entrySet().stream().filter(p -> p.getValue().equals(channel)).findFirst().orElse(null);
        if (map != null) {
            redisService.deleteObject(Constants.MATCHING + map.getKey());
            redisService.deleteObject(Constants.MATCHING_SUCCESS + map.getKey());
        }
    }

    @Override
    public void connect(Channel channel) {

    }

    private void matching(MatchingType matchingType) {


        Long successTime = redisService.getExpire(Constants.MATCHING_SUCCESS + matchingType.getUserId(), TimeUnit.SECONDS);
        if (successTime > 0) {
            return;
        }
        redisService.setCacheObject(Constants.MATCHING + matchingType.getUserId(), matchingType.getUserId(), 5l, TimeUnit.SECONDS);
        Collection<String> keys = redisService.keys(Constants.MATCHING + "*");
        if (CollectionUtil.isNotEmpty(keys)) {
            keys.forEach(model -> {
                if (StringUtils.isNotBlank(model) && !model.equals(Constants.MATCHING + matchingType.getUserId())) {

                    String matching = model.substring(Constants.MATCHING.length());
                    boolean result = NumberUtils.isNumber(matching);
                    if (result) {
                        Integer otherUserId = Integer.valueOf(matching);
                        //成功
                        //异步发送通知消息
                        asyncService.sendMatchMessage(otherUserId, matchingType.getUserId());

                        redisService.setCacheObject(Constants.MATCHING_SUCCESS + matchingType.getUserId(), matchingType.getUserId(), 5l, TimeUnit.MINUTES);
                        redisService.setCacheObject(Constants.MATCHING_SUCCESS + model.substring(Constants.MATCHING.length()), model.substring(Constants.MATCHING.length()), 5l, TimeUnit.MINUTES);

                        redisService.deleteObject(Constants.MATCHING + matchingType.getUserId());
                        redisService.deleteObject(Constants.MATCHING + model.substring(Constants.MATCHING.length()));
                    }

                }
            });
        }
    }
}
