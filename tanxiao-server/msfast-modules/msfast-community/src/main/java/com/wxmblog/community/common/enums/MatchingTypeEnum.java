package com.wxmblog.community.common.enums;

public enum MatchingTypeEnum {
    ORDINARY("普通匹配"),
    SENIOR("高级匹配");
    private String desc;


    MatchingTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
