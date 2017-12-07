package com.jk.provide.entity;

public class MessageBean {

	private String msg;
	private String title;
	private boolean status;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MessageBean [msg=" + msg + ", title=" + title + ", status=" + status + "]";
	}
	
	
}
