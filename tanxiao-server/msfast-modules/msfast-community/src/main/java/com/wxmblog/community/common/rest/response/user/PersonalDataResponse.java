package com.wxmblog.community.common.rest.response.user;

import com.wxmblog.base.common.enums.GenderEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersonalDataResponse {

    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headPortrait;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别 MALE(\"男\"),\n" +
            "    FEMALE(\"女\")")
    private GenderEnum gender;

    /**
     * 登陆账号
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 城市
     */
    @ApiModelProperty(value = "地区")
    private String city;
}
