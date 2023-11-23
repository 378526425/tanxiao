package com.wxmblog.community.controller;

import com.wxmblog.community.service.FrUserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.apache.shiro.authz.annotation.RequiresPermissions;


/**
 * 前台用户关注关系
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-11-12 15:22:41
 */
@RestController
@RequestMapping("community/fruserfollow")
public class FrUserFollowController {
    @Autowired
    private FrUserFollowService frUserFollowService;

}
