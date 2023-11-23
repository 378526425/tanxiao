package com.wxmblog.community.common.rest.response.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PersonalCenterResponse {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String headPortrait;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "ip地址")
    private String city;

    @ApiModelProperty(value = "余额")
    private Integer goldBalance;

    @ApiModelProperty(value = "动态数")
    private Long blogCount;

    @ApiModelProperty(value = "关注数")
    private Long followCount;

    @ApiModelProperty(value = "粉丝")
    private Long fansCount;

    @ApiModelProperty(value = "我的动态")
    private List<PersonalCenterBlogResponse> blog;
}
