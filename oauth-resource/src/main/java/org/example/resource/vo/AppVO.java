package org.example.resource.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppVO implements Serializable {
    private String applicationUrl;
    private String jumpType;
    private boolean selected;
    private String systemId;
    private String systemCode;
    private String systemName;
}
