package com.wxmblog.community.common.constant;

import com.wxmblog.base.common.utils.SpringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Data
//@RefreshScope
public class PropertiesConstants {

    @Value("${tencentTUICallKit.sdkAppID}")
    private Long sdkAppID;

    @Value("${tencentTUICallKit.secretKey}")
    private String secretKey;

    public static Long SDK_APP_ID() {
        return SpringUtils.getBean(PropertiesConstants.class).getSdkAppID();
    }

    public static String SECRET_KEY() {
        return SpringUtils.getBean(PropertiesConstants.class).getSecretKey();
    }
}
