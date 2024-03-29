package org.example.service;

import org.example.Data;
import org.example.model.entity.Menu;

import java.util.Map;

public class MenuServiceImpl implements MenuService{
    @Override
    public Map<Long, Menu> getMenuList() {
        return Data.menuMap;
    }

    @Override
    public Menu read(Long menu) {
        return Data.menuMap.get(menu);
    }
}
