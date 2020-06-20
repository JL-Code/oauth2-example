package org.example.resource.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;


@Data
public class UserVO implements Serializable {
    private String userId;
    private String userName;
    private String userCode;
    private String selectedStationId;
    private Collection<? extends StationVO> stations;


//    public static UserVO of(UserDetailsDTO dto) {
//        UserVO userVO = new UserVO();
//        userVO.setSelectedStationId(dto.getSelectedStationId());
//        userVO.setUserId(dto.getUserId());
//        userVO.setUserCode(dto.getUserCode());
//        userVO.setUserName(dto.getUsername());
//        userVO.setStations(dto.getStations());
//        return userVO;
//    }
}
