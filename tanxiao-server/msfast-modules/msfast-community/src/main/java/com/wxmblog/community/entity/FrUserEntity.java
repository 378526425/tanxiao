package com.wxmblog.community.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wxmblog.base.common.entity.BaseEntity;
import com.wxmblog.base.common.enums.FrUserStatusEnum;
import com.wxmblog.base.common.enums.GenderEnum;
import lombok.Data;

import java.util.Date;


/**
 * @author wanglei
 * @email 378526425@qq.com
 * @date 2022-09-22 16:01:47
 */
@Data
@TableName("fr_user")
public class FrUserEntity extends BaseEntity {

    /**
     * 登陆账号
     */
    @TableField("phone")
    private String phone;
    /**
     * 账号密码
     */
    @TableField("password")
    private String password;
    /**
     * 头像
     */
    @TableField("head_portrait")
    private String headPortrait;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 出生日期
     */
    @TableField("birthday")
    private Date birthday;

    /**
     * 经度
     */
    @TableField("lon")
    private String lon;
    /**
     * 纬度
     */
    @TableField("lat")
    private String lat;
    /**
     * 城市
     */
    @TableField("city")
    private String city;

    @TableField("gender")
    private GenderEnum gender;

    @TableField("status")
    private FrUserStatusEnum status;

    @TableField("gold_balance")
    private Integer goldBalance;

}
