package com.wjb.service;

import com.wjb.base.BaseService;
import com.wjb.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/10/26.
 */
public interface MenuService extends BaseService<Menu,Long> {

    List<Menu> getUrl(@Param("id")Long id);

    List<Menu> rootMenu(@Param("parentId")Integer parentId);
}
