package com.wxmblog.community.common.type;


import com.wxmblog.community.common.enums.MessageTypeEnum;
import lombok.Data;

/**
 * @program: wxm-fast
 * @description:
 * @author: Mr.Wang
 * @create: 2022-10-13 17:42
 **/

@Data
public class MessageInfo {

    private MessageTypeEnum messageType;

    private String info;
}
