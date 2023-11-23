package com.wxmblog.community.common.rest.response.user;

import com.wxmblog.community.common.enums.FollowStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 * @Author
 * @Description  用户关注列表
 * @Date 13:52 2022/11/15
 **/
@Data
public class FollowPageResponse {

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
            "    FRIEND(\"互相关注\")")
    private FollowStatus followStatus;
}
