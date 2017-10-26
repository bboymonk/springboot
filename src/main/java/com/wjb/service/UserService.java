package com.wjb.service;

import com.wjb.base.BaseService;
import com.wjb.model.User;

/**
 * Created by Administrator on 2017/10/26.
 */
public interface UserService extends BaseService<User,Long> {
    /**
     * 登录
    *@Author:wjb
    *@params:
    *@Date:10:23 2017/10/26
    */
    User login(String username,String password);

}
