package com.inacif.rekognition.web.app.maps;

public class ImagesUrl {

	private String principal;
	private String comparedImage;
	
	public ImagesUrl(String principal, String comparedImage) {
		this.principal = principal;
		this.comparedImage = comparedImage;
	}
	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getComparedImage() {
		return comparedImage;
	}
	public void setComparedImage(String comparedImage) {
		this.comparedImage = comparedImage;
	}
	
}
