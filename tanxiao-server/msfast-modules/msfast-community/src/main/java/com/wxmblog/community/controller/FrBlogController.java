package com.wxmblog.community.controller;

//import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.wxmblog.base.common.annotation.AuthIgnore;
import com.wxmblog.base.common.constant.ParamTypeConstants;
import com.wxmblog.base.common.utils.PageResult;
import com.wxmblog.base.common.web.domain.R;
import com.wxmblog.community.common.rest.request.blog.BlogSubmitRequest;
import com.wxmblog.community.common.rest.response.blog.*;
import com.wxmblog.community.service.FrBlogService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 用户日志
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-11-12 17:05:01
 */
@RestController
@RequestMapping("community/frblog")
@Api(tags = "前台用户动态")
public class FrBlogController {

    @Autowired
    private FrBlogService frBlogService;

    @ApiOperation("动态详情")
    @ApiOperationSort(value = 1)
    @GetMapping("/detail/{id}")
    @AuthIgnore
    public R<BlogDetailResponse> detail(@PathVariable Integer id) {
        return R.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("我的-动态列表")
    @ApiOperationSort(value = 2)
    @GetMapping("/mine/blog/page")
    public R<PageResult<BlogPageResponse>> mineBlogPage(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return R.ok();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.pathVariable, name = "blogId", value = "动态id"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("动态列表-评论列表")
    @ApiOperationSort(value = 3)
    @GetMapping("/blog/comment/page/{blogId}")
    @AuthIgnore
    public R<PageResult<CommentPageResponse>> blogCommentPage(
            @PathVariable Integer blogId,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

        PageResult<CommentPageResponse> pageResult = new PageResult<>(null);
        return R.ok(pageResult);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.pathVariable, name = "commentId", value = "评论id"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("动态列表-评论列表-回复列表 从第二条后开始返回")
    @ApiOperationSort(value = 4)
    @GetMapping("/blog/reply/page/{commentId}")
    @AuthIgnore
    public R<PageResult<ReplyResponse>> blogReplyPage(
            @PathVariable Integer commentId,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

        PageResult<ReplyResponse> pageResult = new PageResult<>(null);
        return R.ok(pageResult);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("好友资料-动态列表")
    @ApiOperationSort(value = 5)
    @GetMapping("/friend/blog/page/{userId}")
    @AuthIgnore
    public R<PageResult<BlogPageResponse>> friendBlogPage(
            @PathVariable Integer userId,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return R.ok();
    }


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "lon", value = "经度", defaultValue = ""),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "lat", value = "纬度", defaultValue = "")
    })
    @ApiOperation("附近-动态列表")
    @ApiOperationSort(value = 6)
    @GetMapping("/near/blog/page")
    @AuthIgnore
    public R<PageResult<NearBlogPageResponse>> nearBlogPage(
            @RequestParam(value = "lon", required = false, defaultValue = "") String lon,
            @RequestParam(value = "lat", required = false, defaultValue = "") String lat,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return R.ok();

    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("关注-动态列表")
    @ApiOperationSort(value = 7)
    @GetMapping("/follow/blog/page")
    public R<PageResult<NearBlogPageResponse>> followBlogPage(
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return R.ok();

    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageIndex", value = "页码", defaultValue = "1"),
            @ApiImplicitParam(paramType = ParamTypeConstants.requestParam, name = "pageSize", value = "数量", defaultValue = "10")
    })
    @ApiOperation("好友-动态列表")
    @ApiOperationSort(value = 8)
    @GetMapping("/friend/blog/page")
    public R<PageResult<NearBlogPageResponse>> friendBlogPage(
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

        return R.ok();
    }

    @ApiOperation("发布动态")
    @ApiOperationSort(value = 9)
    @PostMapping("/submit")
    public R<Void> smsLogin(@RequestBody @Valid BlogSubmitRequest request) {

        return R.ok();
    }

}
