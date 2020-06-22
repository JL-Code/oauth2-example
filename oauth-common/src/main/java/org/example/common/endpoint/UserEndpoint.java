package org.example.common.endpoint;

import org.example.common.vo.StationVO;
import org.example.common.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/22 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class UserEndpoint {

    @GetMapping("/api/user/info")
    public UserVO getUserInfo() {
        List<StationVO> stations = new ArrayList<>();

        StationVO station = new StationVO();
        station.setCompanyName("衡泽软件");
        station.setDepartmentName("开发部");
        station.setStationName("开发工程师");

        stations.add(station);

        UserVO user = new UserVO();
        user.setUserId("123");
        user.setUserName("jiangy");
        user.setUserCode("jiangy");
        user.setStations(stations);

        return user;
    }
}
