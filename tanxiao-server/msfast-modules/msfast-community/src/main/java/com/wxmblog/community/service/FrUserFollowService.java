package com.wxmblog.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxmblog.community.entity.FrUserFollowEntity;

/**
 * 前台用户关注关系
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-11-12 15:22:41
 */
public interface FrUserFollowService extends IService<FrUserFollowEntity> {

    /*
     * @Author 我关注数
     * @Description
     * @Date 17:28 2022/11/12
     * @Param
     * @return
     **/
    Long followUserCount();

    /*
     * @Author 粉丝数
     * @Description
     * @Date 19:13 2022/11/12
     * @Param
     * @return
     **/
    Long fansCount();
}

