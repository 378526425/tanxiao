package com.wxmblog.community.common.rest.response.matching;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-11-01 16:56
 **/

@Data
public class MatchSuccessResponse {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "提示信息")
    private String message;

    @ApiModelProperty(value = "自己的头像")
    private String headPortraitSelf;
}
