package com.qk.trains.controller;

import com.qk.trains.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	/**
	 * 登录首页
	 */
	@RequestMapping("/login")
	public String toLogin(Model model, @RequestParam(value = "username", required = false) String username,
						  @RequestParam(value = "password", required = false) String password,
						  HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (userService.toLogin(username, password) != null) {
			return "index";
		} else {
			out.print("<script>alert('登录失败，账号或密码错误!');</script>");
			return "login";
		}
	}
}