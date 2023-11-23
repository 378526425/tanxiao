package com.wxmblog.community.controller;

import com.wxmblog.base.auth.common.rest.response.LoginUserResponse;
import com.wxmblog.base.common.annotation.AuthIgnore;
import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.community.common.rest.request.user.SmsLoginRequest;
import com.wxmblog.community.common.rest.response.user.*;
import com.wxmblog.community.common.enums.FollowStatus;
import com.wxmblog.community.common.rest.request.user.EditPersonalDataRequest;
import com.wxmblog.community.service.FrUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-09-22 15:46:53
 */
@RestController
@Api(tags = "前台用户模块")
@RequestMapping("community/fruser")
public class FrUserController {

    @Autowired
    private FrUserService frUserService;


    @ApiOperation("验证码登陆")
    @ApiOperationSort(value = 1)
    @PostMapping("/sms/login")
    @AuthIgnore
    public R<LoginUserResponse> smsLogin(@RequestBody @Valid SmsLoginRequest request) {
        return R.ok(frUserService.smsLogin(request));
    }

    @ApiOperation("首页动态用户")
    @ApiOperationSort(value = 2)
    @GetMapping("/dynamic/user")
    @AuthIgnore
    public R<List<DynamicUserResponse>> dynamicUser() {
        return R.ok(frUserService.getDynamicUser());
    }

    @ApiOperation("获取登陆用户详细信息")
    @ApiOperationSort(value = 3)
    @GetMapping("/info")
    public R<LoginResponse> info() {
        return R.ok(frUserService.info());
    }

    @ApiOperation("获取匹配提示信息")
    @ApiOperationSort(value = 4)
    @GetMapping("/message")
    public R<List<String>> message() {
        return R.ok(frUserService.message());
    }

    @ApiOperation("开始匹配")
    @ApiOperationSort(value = 5)
    @GetMapping("/start/matching")
    public R<Void> startMatching() {
        frUserService.startMatching();
        return R.ok();
    }

    @ApiOperation("结束匹配")
    @ApiOperationSort(value = 6)
    @GetMapping("/end/matching")
    public R<Void> endMatching() {
        frUserService.endMatching();
        return R.ok();
    }

    @ApiOperation("我的-个人中心")
    @ApiOperationSort(value = 7)
    @GetMapping("/personalCenter")
    public R<PersonalCenterResponse> personalCenter() {
        return R.ok(frUserService.personalCenter());
    }


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("我的-关注列表")
    @ApiOperationSort(value = 8)
    @GetMapping("/follow/page")
    public R<PageResult<FollowPageResponse>> followPage(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return R.ok(this.frUserService.followPage(pageIndex, pageSize));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("我的-粉丝列表")
    @ApiOperationSort(value = 9)
    @GetMapping("/fans/page")
    public R<PageResult<FollowPageResponse>> fansPage(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return R.ok(this.frUserService.fansPage(pageIndex, pageSize));
    }

    @ApiOperation("我的-关注列表-取消关注")
    @ApiOperationSort(value = 10)
    @GetMapping("/cancel/follow/{id}")
    public R<FollowStatus> cancelFollow(@PathVariable Integer id) {
        return R.ok(this.frUserService.cancelFollow(id));
    }

    @ApiOperation("我的-关注列表-关注用户")
    @ApiOperationSort(value = 11)
    @GetMapping("/follow/user/{id}")
    public R<FollowStatus> followUser(@PathVariable Integer id) {
        return R.ok(this.frUserService.followUser(id));
    }

    @ApiOperation("我的-粉丝列表-移除粉丝")
    @ApiOperationSort(value = 12)
    @GetMapping("/remove/fans/{id}")
    public R<FollowStatus> removeFans(@PathVariable Integer id) {
        return R.ok(this.frUserService.removeFans(id));
    }

    @ApiOperation("我的-查询个人资料")
    @ApiOperationSort(value = 13)
    @GetMapping("/personal/data")
    public R<PersonalDataResponse> personalData() {
        //TODO 接口实现
        return R.ok(new PersonalDataResponse());
    }

    @ApiOperation("我的-修改个人资料")
    @ApiOperationSort(value = 14)
    @PutMapping("/edit/personal")
    public R<Void> editPersonal(@RequestBody @Valid EditPersonalDataRequest request) {
        //TODO 接口实现
        return R.ok();
    }

    @ApiOperation("查看好友资料")
    @ApiOperationSort(value = 15)
    @GetMapping("/user/info/{id}")
    @AuthIgnore
    public R<UserInfoResponse> userInfo(@PathVariable Integer id) {
        //TODO 接口实现
        return R.ok(new UserInfoResponse());
    }

    @ApiOperation("获取腾讯视频登录信息")
    @ApiOperationSort(value = 16)
    @GetMapping("/tuicallkit")
    public R<TuiCallKitResponse> tuiCallKit() {
        return R.ok(frUserService.tuiCallKit());

    }
}
