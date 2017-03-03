package com.interaction.service;

import java.util.List;

import com.interaction.vo.MenuVo;

public interface MenuService {

	List<MenuVo> listAllMenu();

	int addMenu(MenuVo menuVo);

}
