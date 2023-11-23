package com.wxmblog.community.common.rest.response.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-09-30 15:50
 **/

@Data
public class DynamicUserResponse {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

    @ApiModelProperty(value = "昵称")
    private String nickName;
}
