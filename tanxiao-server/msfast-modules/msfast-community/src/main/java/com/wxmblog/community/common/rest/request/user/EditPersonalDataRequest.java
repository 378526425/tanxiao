package com.wxmblog.community.common.rest.request.user;

import com.wxmblog.base.file.annotation.FileSave;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EditPersonalDataRequest {

    @ApiModelProperty(value = "id")
    @NotNull
    private Integer id;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @NotBlank
    @FileSave
    private String headPortrait;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @NotBlank
    private String nickName;

}
