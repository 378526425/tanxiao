package com.wxmblog.community.common.rest.response.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersonalCenterBlogResponse {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "时间")
    private String time;
}
