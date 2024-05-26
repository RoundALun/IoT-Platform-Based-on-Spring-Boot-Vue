package com.roundlun.iot.service;

import java.util.List;

public interface ISysUserRoleService {
    public List<Integer> selectRoleIdByUserId(Long userId);
}
