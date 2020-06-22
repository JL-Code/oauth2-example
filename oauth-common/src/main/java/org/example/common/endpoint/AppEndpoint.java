package org.example.common.endpoint;

import org.example.common.vo.AppVO;
import org.example.common.vo.MenuVO;
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
public class AppEndpoint {
    @GetMapping("/api/app/apps")
    public List<AppVO> listApps() {
        List<AppVO> apps = new ArrayList<>();
        AppVO app = new AppVO();
        app.setSelected(true);
        app.setSystemId("123");
        app.setSystemCode("org");
        app.setSystemName("组织管理");
        apps.add(app);
        return apps;
    }

    @GetMapping("/api/app/menus")
    public List<MenuVO> getAppMenus(String appId) {
        List<MenuVO> menus = new ArrayList<>();
        MenuVO menu = new MenuVO();
        menu.setFunctionType(1);
        menu.setUri("/org/organization-user/organization");
        menu.setId("123");
        menu.setName("组织架构");
        menu.setPath("/org/organization-user/organization");
        menu.setNewWindow(false);
        menu.setType("function");
        menu.setChildren(new ArrayList<>());
        menus.add(menu);
        return menus;
    }

}
