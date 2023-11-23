package com.wxmblog.community.common.rest.response.blog;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/*
 * @Author
 * @Description 用户动态列表
 * @Date 21:05 2022/11/17
 **/
@Data
public class BlogPageResponse {

    @ApiModelProperty(value = "动态id")
    private Integer id;

    /**
     * 前台用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 文字内容
     */
    @ApiModelProperty(value = "文字内容")
    private String content;

    /**
     * 图片内容
     */
    @ApiModelProperty(value = "动态图片")
    private List<String> images;

    /**
     * 头像
     */
    @ApiModelProperty(value = "用户头像")
    private String headPortrait;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "时间")
    private String pushTime;

    @ApiModelProperty(value = "点赞数")
    private Long fabulous;

    @ApiModelProperty(value = "评论数")
    private Long comment;

    @ApiModelProperty(value = "是否点赞过")
    private Boolean thumbsUp;
}
