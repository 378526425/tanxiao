package com.wxmblog.community.common.rest.request.blog;

import com.wxmblog.base.file.annotation.FileListSave;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.List;

/*
 * @Author
 * @Description 动态发布
 * @Date 20:00 2022/11/19
 **/
@Data
public class BlogSubmitRequest {

    @ApiModelProperty(value = "文字内容")
    @Length(max = 100)
    private String content;

    /**
     * 图片内容
     */
    @ApiModelProperty(value = "图片内容")
    @Size(max = 9)
    @FileListSave
    private List<String> images;

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
    private String city;
}
