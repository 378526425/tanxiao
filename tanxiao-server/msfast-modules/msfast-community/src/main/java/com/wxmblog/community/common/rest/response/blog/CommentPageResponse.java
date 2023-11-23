package com.wxmblog.community.common.rest.response.blog;

import com.wxmblog.base.common.enums.GenderEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/*
 * @Author
 * @Description  评论列表
 * @Date 22:14 2022/11/17
 **/
@Data
public class CommentPageResponse {

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

    @ApiModelProperty(value = "性别")
    private GenderEnum gender;

    @ApiModelProperty(value = "ip")
    private String city;

    @ApiModelProperty(value = "评论回复列表最多两条")
    private List<ReplyResponse> replyList;

    @ApiModelProperty(value = "是否存在更多回复 true的话会有查看更多按钮")
    private Boolean moreReply;
}
