package com.roundlun.iot.service;

import com.roundlun.iot.domain.entity.SysRoleMenu;

import java.util.List;

public interface ISysRoleMenuService {
    public List<Integer> selectMenuIdListByRoleId(Integer roldId);

}
