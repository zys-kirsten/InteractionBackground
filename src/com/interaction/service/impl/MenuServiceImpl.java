package com.interaction.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interaction.dao.MenuDAO;
import com.interaction.pojo.Menu;
import com.interaction.service.MenuService;
import com.interaction.vo.MenuVo;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Resource
	private MenuDAO menuDAOImpl;
	
	//列出所有的菜单
	@Override
	public List<MenuVo> listAllMenu() {
		
		List<Menu> menus = menuDAOImpl.listAll();
		if (menus == null || menus.size() == 0) {
			return null;
		}
		List<MenuVo> menuVos = p2v(menus);
		return menuVos;
	}

	private List<MenuVo> p2v(List<Menu> menus) {
		if (menus == null) {
			return null;
		}
		
		List<MenuVo> menuVos = new ArrayList<MenuVo>();
		for(Menu menu:menus){
			if (menu != null) {
				menuVos.add(p2v(menu));
			}
		}
		return menuVos;
	}

	private MenuVo p2v(Menu menu) {
		MenuVo menuVo = new MenuVo();
		menuVo.setMid(menu.getMid());
		menuVo.setMname(menu.getMname());
		if (menu.getMenu() != null) {
			Menu father = menuDAOImpl.findById(menu.getMenu().getMid());
			if (father != null) {
				menuVo.setFatherId(father.getMid());
				menuVo.setFatherName(father.getMname());
			}
		}
		return menuVo;
	}


	private Menu v2p(MenuVo menuVo) {
		Menu menu = new Menu();

		menu.setMid(menuVo.getMid());
		menu.setMname(menuVo.getMname());
		
		if (menuVo.getFatherId() != -1) {
			Menu father = menuDAOImpl.findById(menuVo.getFatherId());
			if (father != null) {
				menu.setMenu(father);
			}
		}
		return menu;
	}
	
	//添加菜单
	@Override
	public int addMenu(MenuVo menuVo) {
		if (menuVo == null) {
			return -1;
		}
		
		Menu menu = v2p(menuVo);
		return menuDAOImpl.addMenu(menu);
	}

	@Override
	public MenuVo findById(Integer mid) {

		Menu menu = menuDAOImpl.findById(mid);
		if (menu == null) {
			return null;
		}
		return p2v(menu);
	}
	
	@Override
	public int updateMenu(MenuVo menuVo) {

		if (menuVo == null) {
			return -1;
		}
		
		return menuDAOImpl.updateMenu(v2p(menuVo));
	}
	
	@Override
	public void deleteById(Integer mid) {

		 Menu menu = menuDAOImpl.findById(mid);
		 if (menu == null) {
			return ;
		}
		 menuDAOImpl.deleteMenu(menu);
	}
}
