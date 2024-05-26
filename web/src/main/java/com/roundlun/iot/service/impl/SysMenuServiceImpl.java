package com.roundlun.iot.service.impl;

import com.roundlun.iot.domain.entity.RouterVo;
import com.roundlun.iot.domain.entity.SysMenu;
import com.roundlun.iot.mapper.SysMenuMapper;
import com.roundlun.iot.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements ISysMenuService
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";
    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> selectSysMenuList(SysMenu sysMenu) {
        List<SysMenu> menus = sysMenuMapper.selectSysMenuList(sysMenu);
        return buildTree(menus);
    }

    @Override
    @Transactional
    public List<SysMenu> selectSysMenuListByUserId(Long userId) {
        List<SysMenu> menus = sysMenuMapper.selectSysMenuListByUserId(userId);
        return buildTree(menus);
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<SysMenu> treeMenu = new ArrayList<>();
        List<RouterVo> routerMenu = new ArrayList<>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setComponent(menu.getComponent());
            router.setRedirect(menu.getRedirect());
            router.setPath(menu.getPath());
            router.setName(menu.getName());
            router.setIcon(menu.getIcon());
            router.setHidden(menu.getHidden());
            router.setMeta(menu.getMeta());
            List<SysMenu> cMenus = menu.getChildren();
            if (cMenus != null && !cMenus.isEmpty())
            {
                router.setAlwaysShow(true);
                router.setChildren(buildMenus(cMenus));
            }
            routerMenu.add(router);
        }
        return routerMenu;
    }



    private List<SysMenu> buildTree(List<SysMenu> menus) {
        List<SysMenu> treeMenu = new ArrayList<>();
        for (SysMenu menu : menus) {
            if (menu.getParentId() == 0) {
                treeMenu.add(menu);
            }
            for (SysMenu m : menus) {
                if (m.getParentId() != null && m.getParentId().equals(menu.getMenuId())) {
                    if (menu.getChildren() == null) {
                        menu.setChildren(new ArrayList<>());
                    }
                    menu.getChildren().add(m);
                }
            }
        }
        return treeMenu;
    }


}
