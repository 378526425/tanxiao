package com.wxmblog.community.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.wxmblog.base.common.entity.BaseEntity;
import lombok.Data;


/**
 * 前台用户关注关系
 *
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-11-12 15:22:41
 */
@Data
@TableName("fr_user_follow")
public class FrUserFollowEntity extends BaseEntity {

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 关注者id
     */
    @TableField("follow_id")
    private Integer followId;

}
