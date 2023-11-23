package com.wxmblog.community.common.rest.response.blog;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BlogDetailResponse extends BlogPageResponse {

    @ApiModelProperty(value = "是否有删除按钮")
    private Boolean deleteBtn;
}
