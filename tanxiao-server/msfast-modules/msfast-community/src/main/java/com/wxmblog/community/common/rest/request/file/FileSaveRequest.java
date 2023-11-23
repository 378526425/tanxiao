package com.wxmblog.community.common.rest.request.file;

import com.wxmblog.base.file.annotation.FileSave;
import lombok.Data;

@Data
public class FileSaveRequest {

    @FileSave
    private String fileUrl;
}
