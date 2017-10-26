package com.wjb.service.impl;

import com.wjb.base.BaseMapper;
import com.wjb.base.BaseServiceImpl;
import com.wjb.mapper.MenuMapper;
import com.wjb.model.Menu;
import com.wjb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 获取所有菜单
    *@Author:
    *@params:
    *@Date:17:17 2017/10/26
    */
    @Override
    public List<Menu> rootMenu(Integer parentId) {
        parentId = parentId == null ? 0 :parentId;
        //所有菜单
        List<Menu> rootMenu = menuMapper.rootMenu(parentId);
        //最后的菜单
        List<Menu> menu = new ArrayList<>();
        for (int i = 0;i<rootMenu.size();i++){
            if (rootMenu.get(i).getParentId().equals(parentId)){
                menu.add(rootMenu.get(i));
            }
        }
        //为一级菜单设置子菜单
        for(int i = 0;i<menu.size();i++){
            menu.get(i).setChildMenu(getChild(menu.get(i).getId(),rootMenu));
        }
        return menu;
    }

    /**
     * 设置子菜单
     * @param id
     * @param rootMenu
     * @return
     */
    public List<Menu> getChild(Integer id,List<Menu> rootMenu){
        List<Menu> childMenu = new ArrayList<>();
        for (int i = 0;i<rootMenu.size();i++){
            if (null != rootMenu.get(i).getParentId() && rootMenu.get(i).getParentId() != 0){
                if (rootMenu.get(i).getParentId().equals(id)){
                    childMenu.add(rootMenu.get(i));
                }
            }
        }
        //如果有三级菜单，再遍历一次
        for (Menu menu:childMenu){
            if (null != menu.getParentId()){
                if (menu.getParentId().equals(id)){
                    menu.setChildMenu(getChild(menu.getId(),rootMenu));
                }
            }
        }
        if (childMenu.size() == 0){
            return null;
        }
        return childMenu;
    }



}
