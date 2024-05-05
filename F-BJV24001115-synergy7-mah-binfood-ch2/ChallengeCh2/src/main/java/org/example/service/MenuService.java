package org.example.service;

import org.example.model.entity.Menu;

import java.util.Map;

public interface MenuService {
    Map<Long, Menu> getMenuList();
    Menu read(Long menu);
}
