package com.inacif.rekognition.web.app.responses;

import java.util.ArrayList;
import java.util.List;

public class MapMenuResultResponse {

	List<MapMenuResult> menu = new ArrayList<>();

	public void pushToMenu(MapMenuResult functionality) {
		this.menu.add(functionality);
	}
	
	public List<MapMenuResult> getMenu() {
		return menu;
	}

	public void setMenu(List<MapMenuResult> menu) {
		this.menu = menu;
	}
	
	
	
}
