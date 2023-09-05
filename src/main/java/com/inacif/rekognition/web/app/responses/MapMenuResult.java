package com.inacif.rekognition.web.app.responses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inacif.rekognition.web.app.entity.SPMenu;

public class MapMenuResult {

	@JsonProperty("principal")
	SPMenu menu;
	List<SPMenu> subMenus = new ArrayList<>();
	
	public MapMenuResult(SPMenu menu, List<SPMenu> subMenus) {
		this.menu = menu;
		this.subMenus = subMenus;
	}
	
	public SPMenu getMenu() {
		return menu;
	}
	public void setMenu(SPMenu menu) {
		this.menu = menu;
	}
	public List<SPMenu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<SPMenu> subMenus) {
		this.subMenus = subMenus;
	}
	
	
	
}
