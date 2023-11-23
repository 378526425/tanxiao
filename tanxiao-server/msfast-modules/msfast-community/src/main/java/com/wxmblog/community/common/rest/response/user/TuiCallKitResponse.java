package com.wxmblog.community.common.rest.response.user;

import lombok.Data;

@Data
public class TuiCallKitResponse {

    private Long sdkAppId;

    private String userId;

    private String userSig;

    private String nickName;

    private String avatar;

    private String callingBell;
}
