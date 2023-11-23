package com.wxmblog.community.common.rest.request.user;

import com.wxmblog.base.auth.common.rest.request.RegisterRequest;
import com.wxmblog.base.common.enums.GenderEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-09-22 18:07
 **/

@Data
public class UserRegisterRequest extends RegisterRequest {

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headPortrait;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @Length(min = 1, max = 8)
    @NotBlank
    private String nickName;
    /**
     * 出生日期
     */
    @Past(message = "出生日期必须是过去")
    @NotNull(message = "出生日期不可为空")
    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String lon;
    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String lat;
    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    @NotBlank
    private String city;

    @ApiModelProperty(value = "性别")
    @NotNull
    private GenderEnum gender;
}
