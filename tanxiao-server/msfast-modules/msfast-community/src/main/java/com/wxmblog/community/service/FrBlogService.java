package com.wxmblog.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxmblog.community.entity.FrBlogEntity;
import com.wxmblog.community.common.rest.response.user.PersonalCenterBlogResponse;

import java.util.List;

/**
 * 用户日志
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-11-12 17:05:01
 */
public interface FrBlogService extends IService<FrBlogEntity> {

    /*
     * @Author 用户发布的动态数量
     * @Description
     * @Date 17:12 2022/11/12
     * @Param
     * @return
     **/
    Long userBlogCount();

    /*
     * @Author 查询个人中心的日志预览
     * @Description
     * @Date 20:13 2022/11/12
     * @Param
     * @return
     **/
    List<PersonalCenterBlogResponse> getPersonalBlogImage();
}

