package org.example.oauth2.security.client;

/**
 * <p>创建时间: 2020/7/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public enum AppType {

    DINGTALK_SCANCODE_APP(1, "钉钉扫码登录应用"),
    CORP_WECHAT_APP(2, "企业微信应用");

    private int enumValue;
    private String enumName;

    AppType(int enumValue, String enumName) {
        this.enumValue = enumValue;
        this.enumName = enumName;
    }

    public int getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(int enumValue) {
        this.enumValue = enumValue;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }
}