package com.wxmblog.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxmblog.base.auth.common.rest.response.LoginUserResponse;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.community.common.rest.request.user.SmsLoginRequest;
import com.wxmblog.community.common.rest.response.user.*;
import com.wxmblog.community.common.enums.FollowStatus;
import com.wxmblog.community.entity.FrUserEntity;

import java.util.List;

/**
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-09-22 15:46:53
 */
public interface FrUserService extends IService<FrUserEntity> {

    Long countByPhone(String phone);

    FrUserEntity getFrUserByPhone(String phone);

    LoginUserResponse smsLogin(SmsLoginRequest request);

    List<DynamicUserResponse> getDynamicUser();

    /**
     * @Description: 获取当前登陆用户信息
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2022/10/10 下午2:18
     */
    LoginResponse info();

    /**
     * @Description: 获取匹配提示信息
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2022/10/10 下午4:40
     */
    List<String> message();

    void startMatching();

    void endMatching();

    /*
     * @Author wanglei
     * @Description  个人中心
     * @Date 15:38 2022/11/15
     * @Param
     * @return
     **/
    PersonalCenterResponse personalCenter();

    /*
     * @Author wanglei
     * @Description  我的关注列表
     * @Date 15:38 2022/11/15
     * @Param
     * @return
     **/
    PageResult<FollowPageResponse> followPage(Integer pageIndex, Integer pageSize);

    /*
     * @Author wanglei
     * @Description  关注我的列表
     * @Date 20:23 2022/11/15
     * @Param
     * @return
     **/
    PageResult<FollowPageResponse> fansPage(Integer pageIndex, Integer pageSize);

    /*
     * @Author wanglei
     * @Description  取消关注
     * @Date 20:40 2022/11/15
     * @Param
     * @return
     **/
    FollowStatus cancelFollow(Integer id);

    /*
     * @Author wanglei
     * @Description  关注用户
     * @Date 20:46 2022/11/15
     * @Param
     * @return
     **/
    FollowStatus followUser(Integer id);

    /*
     * @Author wanglei
     * @Description  移除粉丝
     * @Date 20:53 2022/11/15
     * @Param
     * @return
     **/
    FollowStatus removeFans(Integer id);

    /*
     * @Author wanglei
     * @Description  腾讯视频配置文件
     * @Date 21:19 2022/11/20
     * @Param
     * @return
     **/
    TuiCallKitResponse tuiCallKit();
}

