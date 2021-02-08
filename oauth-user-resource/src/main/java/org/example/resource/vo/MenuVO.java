package org.example.resource.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/20 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {
    private String path;
    private List<MenuVO> children;
    private String name;
    private String icon;
    private String id;
    private int functionType;
    private String type;
    private String uri;
    private boolean newWindow;
}
