package com.wxmblog.community.service.impl;

import com.alibaba.fastjson.JSON;
import com.wxmblog.base.websocket.utils.ChannelUtil;
import com.wxmblog.community.common.rest.response.matching.MatchSuccessResponse;
import com.wxmblog.community.entity.FrUserEntity;
import com.wxmblog.community.service.AsyncService;
import com.wxmblog.community.service.FrUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    FrUserService frUserService;

    @Autowired
    ChannelUtil channelUtil;

    @Override
    @Async
    public void sendMatchMessage(Integer otherUserId, Integer selfUserId) {

        FrUserEntity otherUser = frUserService.getById(otherUserId);
        FrUserEntity selfUser = frUserService.getById(selfUserId);
        if (otherUser != null && selfUser != null) {
            sendChannel(selfUser, otherUser);
            sendChannel(otherUser, selfUser);
        }
    }

    private void sendChannel(FrUserEntity selfUser, FrUserEntity otherUser) {
        MatchSuccessResponse matchSuccessResponse = new MatchSuccessResponse();
        BeanUtils.copyProperties(otherUser, matchSuccessResponse);
        matchSuccessResponse.setId(String.valueOf(otherUser.getId()));
        matchSuccessResponse.setHeadPortraitSelf(selfUser.getHeadPortrait());
        matchSuccessResponse.setMessage("恭喜你,匹配成功");
        channelUtil.sendText(selfUser.getId(), JSON.toJSONString(matchSuccessResponse));
    }
}
