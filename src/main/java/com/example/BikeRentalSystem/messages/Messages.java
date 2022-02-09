package com.example.BikeRentalSystem.messages;

public class Messages {

	private String content;
	private String type;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Messages(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}
	public Messages() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
