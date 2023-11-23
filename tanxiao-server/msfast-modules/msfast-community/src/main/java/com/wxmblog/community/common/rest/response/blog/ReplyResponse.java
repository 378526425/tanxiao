package com.wxmblog.community.common.rest.response.blog;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 * @Author
 * @Description  回复内容
 * @Date 13:27 2022/11/18
 **/
@Data
public class ReplyResponse {

    @ApiModelProperty(value = "评论id")
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
     * 昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "ip")
    private String city;

    @ApiModelProperty(value = "时间")
    private String pushTime;

    /**
     * 前台用户id
     */
    @ApiModelProperty(value = "回复用户id")
    private Integer replyId;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "回复用户昵称")
    private String replyNickName;
}
