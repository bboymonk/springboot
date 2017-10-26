package com.wjb.service.impl;

import com.wjb.base.BaseMapper;
import com.wjb.base.BaseServiceImpl;
import com.wjb.mapper.MenuMapper;
import com.wjb.model.Menu;
import com.wjb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,Long> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public BaseMapper getMapper() {
        return menuMapper;
    }

    @Override
    public List<Menu> getUrl(Long id) {
        return menuMapper.getUrl(id);
    }
}
