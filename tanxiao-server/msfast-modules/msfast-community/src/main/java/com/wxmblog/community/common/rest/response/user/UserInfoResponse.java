package com.wxmblog.community.common.rest.response.user;

import com.wxmblog.community.common.enums.FollowStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoResponse {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "ip地址")
    private String city;

    @ApiModelProperty(value = "关注状态 FOLLOW(\"我关注\"),\n" +
            "    FANS(\"关注我\"),\n" +
            "    FRIEND(\"互相关注\"),\n" +
            "    STRANGER(\"陌生人\")")
    private FollowStatus followStatus;
}
