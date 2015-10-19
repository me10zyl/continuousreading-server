package net.xicp.zyl_me.continuous.reading.bal.controller;

import java.util.List;

import net.xicp.zyl_me.continuous.reading.bal.entity.Message;
import net.xicp.zyl_me.continuous.reading.dal.dao.UserDAO;
import net.xicp.zyl_me.continuous.reading.dal.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/android/user")
public class AndroidUserController {
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Message login(@RequestBody User user)
	{
		Message message = new Message();
		List<User> users = userDAO.findByUsername(user.getUsername());
		if(users.size() > 0)//有用户名
		{
			User userFound = users.get(0);
			if(user.getPassword().equals(userFound.getPassword()))//登陆成功
			{
				message.setSuccess(true);
				message.setUser(userFound);
				message.setMessage("Welcome, <font color=red>"+user.getUsername()+"</font>");
				return message;
			}
			message.setMessage("password not correct");//密码错误
		}else{
			message.setMessage("username do not exist");
		}
		return message;
	}

	@RequestMapping(value="/register",method={RequestMethod.POST})
	public Message register(@RequestBody User user)
	{
		Message message = new Message();
		List<User> existUsers = userDAO.findByUsername(user.getUsername());
		if(existUsers.size() > 0)
		{
			message.setMessage("this username has been registered");
			return message;
		}
		userDAO.save(user);
		message.setSuccess(true);
		message.setUser(user);
		message.setMessage("Congradulations!<font color=red>" + user.getUsername() +"</font> register success.");
		return message;
	}
}
