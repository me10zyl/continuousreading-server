package net.xicp.zyl_me.continuous.reading.bal.controller;

import java.util.List;

import net.xicp.zyl_me.continuous.reading.dal.dao.UserDAO;
import net.xicp.zyl_me.continuous.reading.dal.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,Model model)
	{
		List<User> users = userDAO.findByUsername(username);
		if(users.size() > 0)//有用户名
		{
			User user = users.get(0);
			if(password.equals(user.getPassword()))//登陆成功
			{
				model.addAttribute("username", username);
				model.addAttribute("password", password);
				model.addAttribute("message", "Welcome, <font color=red>"+username+"</font>");
				return "logined";
			}
			model.addAttribute("message", "password not correct");//密码错误
		}else{
			model.addAttribute("message", "username do not exist");
		}
		return "error";
	}

	@RequestMapping(value="/register",method={RequestMethod.GET,RequestMethod.POST})
	public String register(@RequestParam("username") String username, @RequestParam("password") String password,Model model)
	{
		List<User> existUsers = userDAO.findByUsername(username);
		if(existUsers.size() > 0)
		{
			model.addAttribute("message","this username has been registered");
			return "error";
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userDAO.save(user);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("message", "Congradulations!<font color=red>" + username +"</font> register success.");
		return "logined";
	}
}
