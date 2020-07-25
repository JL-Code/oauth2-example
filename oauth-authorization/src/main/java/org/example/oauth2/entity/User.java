package org.example.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/21 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
@TableName("sys_user")
public class User {
    private String id;
    @TableField("user_code")
    private String userCode;
    private String password;
    private String name;
    @TableField("mobile_phone")
    private String phoneNumber;
    @TableField("wechat_account")
    private String corpWxUserId;
    @TableField("ding_talk_account")
    private String dingtalkUserId;
}
