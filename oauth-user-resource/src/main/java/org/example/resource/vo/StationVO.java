package org.example.resource.vo;

import lombok.Data;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/20 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class StationVO {
    private String id;
    private String stationName;
    private String departmentId;
    private String departmentName;
    private String companyId;
    private String companyName;
}
