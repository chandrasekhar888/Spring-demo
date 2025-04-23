package com.demo.Dto;

import java.util.Date;

public class ErrorDetails {

	private String message;
	private Date data;
	private String url;
	public ErrorDetails(String message, Date data, String url) {
		
		this.message = message;
		this.data = data;
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public Date getData() {
		return data;
	}
	public String getUrl() {
		return url;
	}
	
	
}
