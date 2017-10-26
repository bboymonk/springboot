package com.wjb.service.impl;

import com.wjb.base.BaseMapper;
import com.wjb.base.BaseServiceImpl;
import com.wjb.mapper.RoleMapper;
import com.wjb.model.Role;
import com.wjb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public BaseMapper getMapper() {
        return roleMapper;
    }

    @Override
    public List<Role> roleList(Integer uid) {
        return roleMapper.roleList(uid);
    }
}
