package com.wxmblog.community.common.rest.request.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-09-29 17:36
 **/

@Data
public class SmsLoginRequest {

    @NotBlank
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号格式不正确")
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 验证码
     */
    @NotBlank
    private String code;
}
