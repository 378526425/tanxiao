package com.wxmblog.community.common.enums;

public enum SysConfigEnum {
    video_matching_tips("视频匹配提示信息"),
    home_bgm("背景音乐");
    private String desc;


    SysConfigEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
