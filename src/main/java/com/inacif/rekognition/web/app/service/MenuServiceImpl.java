package com.inacif.rekognition.web.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inacif.rekognition.web.app.entity.SPMenu;
import com.inacif.rekognition.web.app.responses.MapMenuResult;
import com.inacif.rekognition.web.app.responses.MapMenuResultResponse;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	QueryService queryService;
	
	@Override 
	public Object MapMenu(Integer roleId) {
		List<SPMenu> menu = queryService.callSp_getMenuByRole(roleId);
		if(menu.isEmpty()) {
			return null;
		}
		
		MapMenuResultResponse menuResponse = new MapMenuResultResponse();
		for(SPMenu menuOption: menu) {
			if(menuOption.getFatherId() == null) {
				List<SPMenu> menuOptions = new ArrayList<>();
				Long currentFather = menuOption.getId();
				for(SPMenu subMenu: menu) {
					if(subMenu.getFatherId() != null && subMenu.getFatherId() == currentFather.intValue()) {
						menuOptions.add(subMenu);
					}
				}
				MapMenuResult menuResult = new MapMenuResult(menuOption, menuOptions);
				menuResponse.pushToMenu(menuResult);
			}
		}
		
		return menuResponse;
	}

	
	
}
