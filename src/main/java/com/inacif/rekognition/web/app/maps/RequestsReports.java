package com.inacif.rekognition.web.app.maps;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class RequestsReports {

	@Valid
	@NotNull(message = "reportId is required")
	private int reportId;
	
	@Valid
	@NotNull(message = "initDate is required")
	String initDate;
	
	String endDate;
	
	String condition;
	
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getInitDate() {
		return initDate;
	}
	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
