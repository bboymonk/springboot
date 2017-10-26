package com.wjb.service;

import com.wjb.base.BaseService;
import com.wjb.model.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */
public interface RoleService extends BaseService<Role,Long> {
    List<Role> roleList(Integer uid);
}
