package org.example.resource2;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class Resource implements Serializable {
    private String id;
    private String name;
}
