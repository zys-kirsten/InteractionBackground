package com.interaction.dao;

import java.util.List;

import com.interaction.pojo.Menu;

public interface MenuDAO {

	Menu findById(Integer mid);

	List<Menu> listAll();

	int addMenu(Menu menu);

	int updateMenu(Menu v2p);

	void deleteMenu(Menu menu);

}
