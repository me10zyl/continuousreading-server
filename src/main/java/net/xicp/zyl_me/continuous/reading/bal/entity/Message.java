package net.xicp.zyl_me.continuous.reading.bal.entity;

import java.util.List;

import net.xicp.zyl_me.continuous.reading.dal.entity.Url;
import net.xicp.zyl_me.continuous.reading.dal.entity.User;

public class Message {
	private boolean success;
	private String message;
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private List<Url> urls;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public List<Url> getUrls() {
		return urls;
	}
	public void setUrls(List<Url> urls2) {
		this.urls = urls2;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
