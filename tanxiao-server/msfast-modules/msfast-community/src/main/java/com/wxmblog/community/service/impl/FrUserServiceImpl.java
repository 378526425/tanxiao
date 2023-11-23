package com.wxmblog.community.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wxmblog.base.auth.common.enums.MessageType;
import com.wxmblog.base.auth.common.rest.request.CheckSmsRequest;
import com.wxmblog.base.auth.common.rest.response.LoginUserResponse;
import com.wxmblog.base.auth.service.MsfConfigService;
import com.wxmblog.base.auth.service.TokenService;
import com.wxmblog.base.common.entity.LoginUser;
import com.wxmblog.base.common.enums.BaseUserExceptionEnum;
import com.wxmblog.base.common.exception.JrsfException;
import com.wxmblog.base.common.service.RedisService;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.utils.TokenUtils;
import com.wxmblog.base.file.annotation.FileSaveService;
import com.wxmblog.community.common.enums.SysConfigEnum;
import com.wxmblog.community.common.rest.request.user.SmsLoginRequest;
import com.wxmblog.community.common.rest.request.user.UserLoginRequest;
import com.wxmblog.community.common.rest.response.user.*;
import com.wxmblog.community.dao.FrUserDao;
import com.wxmblog.community.common.constant.Constants;
import com.wxmblog.community.common.constant.PropertiesConstants;
import com.wxmblog.community.common.enums.FollowStatus;
import com.wxmblog.community.entity.FrUserEntity;
import com.wxmblog.community.entity.FrUserFollowEntity;
import com.wxmblog.community.service.FrBlogService;
import com.wxmblog.community.service.FrUserFollowService;
import com.wxmblog.community.service.FrUserService;
import com.wxmblog.community.utils.TLSSigAPIv2;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("frUserService")
public class FrUserServiceImpl extends ServiceImpl<FrUserDao, FrUserEntity> implements FrUserService {

    @Autowired
    TokenService tokenService;

    @Autowired
    MsfConfigService msfConfigService;

    @Autowired
    FrBlogService frBlogService;

    @Autowired
    FrUserFollowService frUserFollowService;

    @Resource
    private RedisService redisService;

    @Autowired
    RedissonClient redissonClient;

    /**
     * @Description: 根据手机号查询数量
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2022/9/29 下午2:53
     */
    @Override
    public Long countByPhone(String phone) {
        Wrapper<FrUserEntity> frUserEntityWrapper = new QueryWrapper<FrUserEntity>().lambda().eq(FrUserEntity::getPhone, phone);
        return count(frUserEntityWrapper);
    }

    @Override
    public FrUserEntity getFrUserByPhone(String phone) {
        Wrapper<FrUserEntity> frUserEntityWrapper = new QueryWrapper<FrUserEntity>().lambda().eq(FrUserEntity::getPhone, phone);
        return getOne(frUserEntityWrapper);
    }

    @Override
    public LoginUserResponse smsLogin(SmsLoginRequest request) {

        FrUserEntity frUserEntity = getFrUserByPhone(request.getPhone());
        if (frUserEntity == null) {
            throw new JrsfException(BaseUserExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        }
        CheckSmsRequest checkSmsRequest = new CheckSmsRequest();
        checkSmsRequest.setMessageType(MessageType.LOGIN);
        BeanUtils.copyProperties(request, checkSmsRequest);
        tokenService.checkSms(checkSmsRequest);

        UserLoginRequest userLoginRequest = new UserLoginRequest();


        userLoginRequest.setUsername(request.getPhone());
        userLoginRequest.setPassword(frUserEntity.getPassword());

        return tokenService.login(userLoginRequest);
    }

    @Override
    public List<DynamicUserResponse> getDynamicUser() {
        Map<String, Object> param = new HashMap<>();
        Integer loginUserId = TokenUtils.getOwnerId();
        if (loginUserId != null) {
            FrUserEntity frUserEntity = getById(loginUserId);
            if (frUserEntity != null) {
                param.put("id", frUserEntity.getId());
                param.put("gender", frUserEntity.getGender().name());
            }
        }

        return this.baseMapper.getDynamicUser(param);
    }

    @Override
    public LoginResponse info() {
        LoginUser<LoginResponse> loginUser = TokenUtils.info(LoginResponse.class);
        return loginUser.getInfo();
    }

    /**
     * @Description: 获取匹配提示信息
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2022/10/10 下午4:40
     */
    @Override
    public List<String> message() {
        String value = msfConfigService.getValueByCode(SysConfigEnum.video_matching_tips.name());
        if (StringUtils.isNotBlank(value)) {
            return JSON.parseArray(value, String.class);
        }
        return null;
    }

    @Override
    public void startMatching() {
        redisService.deleteObject(Constants.MATCHING_SUCCESS + TokenUtils.getOwnerId());
    }

    @Override
    public void endMatching() {
        redisService.deleteObject(Constants.MATCHING + TokenUtils.getOwnerId());
    }

    @Override
    @FileSaveService
    public PersonalCenterResponse personalCenter() {

        Integer loginUserId = TokenUtils.getOwnerId();
        FrUserEntity frUserEntity = this.baseMapper.selectById(loginUserId);
        if (frUserEntity == null) {
            throw new JrsfException(BaseUserExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        }
        PersonalCenterResponse personalCenterResponse = new PersonalCenterResponse();
        BeanUtils.copyProperties(frUserEntity, personalCenterResponse);

        personalCenterResponse.setBlogCount(this.frBlogService.userBlogCount());
        personalCenterResponse.setFollowCount(this.frUserFollowService.followUserCount());
        personalCenterResponse.setFansCount(this.frUserFollowService.fansCount());

        personalCenterResponse.setBlog(frBlogService.getPersonalBlogImage());
        return personalCenterResponse;
    }

    @Override
    public PageResult<FollowPageResponse> followPage(Integer pageIndex, Integer pageSize) {

        Page<FollowPageResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.getBaseMapper().getFollowPage(TokenUtils.getOwnerId());
        PageResult<FollowPageResponse> result = new PageResult<>(page);

        return result;
    }

    @Override
    public PageResult<FollowPageResponse> fansPage(Integer pageIndex, Integer pageSize) {

        Page<FollowPageResponse> page = PageHelper.startPage(pageIndex, pageSize);
        this.getBaseMapper().getFansPage(TokenUtils.getOwnerId());
        PageResult<FollowPageResponse> result = new PageResult<>(page);

        return result;

    }

    @Override
    @Transactional
    public FollowStatus cancelFollow(Integer id) {

        Wrapper<FrUserFollowEntity> wrapper = new QueryWrapper<FrUserFollowEntity>().lambda().eq(FrUserFollowEntity::getUserId, TokenUtils.getOwnerId())
                .eq(FrUserFollowEntity::getFollowId, id);
        this.frUserFollowService.remove(wrapper);

        Wrapper<FrUserFollowEntity> fansWrapper = new QueryWrapper<FrUserFollowEntity>().lambda().eq(FrUserFollowEntity::getUserId, id)
                .eq(FrUserFollowEntity::getFollowId, TokenUtils.getOwnerId());
        Long count = this.frUserFollowService.count(fansWrapper);
        if (count > 0) {
            return FollowStatus.FANS;
        } else {
            return FollowStatus.STRANGER;
        }
    }

    @Override
    @Transactional
    public FollowStatus followUser(Integer id) {
        RLock lock = redissonClient.getLock(Constants.FOLLOW_USER + TokenUtils.getOwnerId());
        try {
            lock.lock();
            Wrapper<FrUserFollowEntity> wrapper = new QueryWrapper<FrUserFollowEntity>().lambda().eq(FrUserFollowEntity::getUserId, TokenUtils.getOwnerId())
                    .eq(FrUserFollowEntity::getFollowId, id);
            Long count = this.frUserFollowService.count(wrapper);
            if (count == 0) {
                FrUserFollowEntity frUserFollowEntity = new FrUserFollowEntity();
                frUserFollowEntity.setFollowId(id);
                frUserFollowEntity.setUserId(TokenUtils.getOwnerId());
                frUserFollowService.save(frUserFollowEntity);
            }
        } finally {
            lock.unlock();
        }
        Wrapper<FrUserFollowEntity> fansWrapper = new QueryWrapper<FrUserFollowEntity>().lambda().eq(FrUserFollowEntity::getUserId, id)
                .eq(FrUserFollowEntity::getFollowId, TokenUtils.getOwnerId());
        Long count = this.frUserFollowService.count(fansWrapper);
        if (count > 0) {
            return FollowStatus.FRIEND;
        } else {
            return FollowStatus.FOLLOW;
        }
    }

    @Override
    @Transactional
    public FollowStatus removeFans(Integer id) {
        Wrapper<FrUserFollowEntity> wrapper = new QueryWrapper<FrUserFollowEntity>().lambda().eq(FrUserFollowEntity::getUserId, id)
                .eq(FrUserFollowEntity::getFollowId, TokenUtils.getOwnerId());
        this.frUserFollowService.remove(wrapper);

        Wrapper<FrUserFollowEntity> fansWrapper = new QueryWrapper<FrUserFollowEntity>().lambda().eq(FrUserFollowEntity::getUserId, TokenUtils.getOwnerId())
                .eq(FrUserFollowEntity::getFollowId, id);
        Long count = this.frUserFollowService.count(fansWrapper);
        if (count > 0) {
            return FollowStatus.FOLLOW;
        } else {
            return FollowStatus.STRANGER;
        }
    }

    @Override
    public TuiCallKitResponse tuiCallKit() {
        TuiCallKitResponse tuiCallKitResponse = new TuiCallKitResponse();
        tuiCallKitResponse.setSdkAppId(PropertiesConstants.SDK_APP_ID());
        tuiCallKitResponse.setUserId(TokenUtils.getOwnerId().toString());
        TLSSigAPIv2 tlsSigAPIv2 = new TLSSigAPIv2(tuiCallKitResponse.getSdkAppId(), PropertiesConstants.SECRET_KEY());
        tuiCallKitResponse.setUserSig(tlsSigAPIv2.genUserSig(TokenUtils.getOwnerId().toString(), 86400));

        LoginResponse loginResponse = TokenUtils.info(LoginResponse.class).getInfo();
        tuiCallKitResponse.setNickName(loginResponse.getNickName());
        tuiCallKitResponse.setAvatar(loginResponse.getHeadPortrait());
        tuiCallKitResponse.setCallingBell(msfConfigService.getValueByCode(SysConfigEnum.home_bgm.name()));
        return tuiCallKitResponse;
    }

}
