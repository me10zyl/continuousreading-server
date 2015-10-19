package net.xicp.zyl_me.continuous.reading.bal.controller;

import java.util.List;

import net.xicp.zyl_me.continuous.reading.bal.entity.Message;
import net.xicp.zyl_me.continuous.reading.dal.dao.UrlDAO;
import net.xicp.zyl_me.continuous.reading.dal.entity.Url;
import net.xicp.zyl_me.continuous.reading.dal.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/android/url")
public class AndroidUrlController {
	@Autowired
	private UrlDAO urlDAO;

	@RequestMapping("/add")
	public Message add(@RequestParam("url") String urlname, @RequestParam("userid") int userid)
	{
		Message message = new Message();
		message.setSuccess(true);
		message.setMessage("add url success");
		Url url = new Url();
		url.setName(urlname);
		User user = new User();
		user.setId(userid);
		url.setUser(user);
		List<Url> urls = urlDAO.findByUrlname(urlname);
		if(urls.size() > 0)
		{
			message.setSuccess(false);
			message.setMessage("this url has existed");
		}else{
			urlDAO.save(url);
		}
		return message;
	}

	@RequestMapping("/delete")
	public Message delete(@RequestParam("urlid") String urlid)
	{
		Message message = new Message();
		message.setSuccess(true);
		message.setMessage("delete url success");
		Url url = new Url();
		url.setId(urlid);
		urlDAO.delete(url);
		return message;
	}

	@RequestMapping("/list")
	public Message list(@RequestParam int userid)
	{
		Message message= new Message();
		message.setSuccess(true);
		message.setMessage("list url success");
		List<Url> urls = urlDAO.listByUserId(userid);
		message.setUrls(urls);
		return message;
	}
}
