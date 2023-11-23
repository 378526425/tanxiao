package com.wxmblog.community.common.enums;

public enum MessageTypeEnum {
    MATCHING("视频匹配");
    private String desc;


    MessageTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
