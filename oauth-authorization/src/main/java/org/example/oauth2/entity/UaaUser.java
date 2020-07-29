package org.example.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_user")
public class UaaUser implements Serializable {
    private String id;
    @TableField("user_code")
    private String userCode;
    private String password;
    private String name;
    @TableField("mobile_phone")
    private String phoneNumber;
    @TableField("wechat_account")
    private String corpWeChatUserId;
    @TableField("ding_talk_account")
    private String dingtalkUserId;
    @TableField("is_disabled")
    private boolean disabled;
    @TableField(value = "is_deleted")
    private boolean deleted;
}
