package com.weifuwukt.distrbute.session.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestSessionController {
	@Value("${server.port}")
	private String serverPort;

	@RequestMapping("/")
	public String index() {
		return serverPort;
	}

	// 创建session 会话
	@RequestMapping("/createSession")
	public String createSession(HttpServletRequest request, String nameValue) {
		HttpSession session = request.getSession();
		System.out.println(
				"存入Session  sessionid:信息" + session.getId() + ",nameValue:" + nameValue + ",serverPort:" + serverPort);
		session.setAttribute("name", nameValue);
		return "success-" + serverPort;
	}

	// 获取session 会话
	@RequestMapping("/getSession")
	public Object getSession(HttpServletRequest request) {
//		HttpSession session = request.getSession();//如果客户端传过来的sessionid没有找到session，则会创建session
		HttpSession session = request.getSession(false);//flase表示:如果客户端传过来的sessionid没有找到session，则不会创建session*/
		if (session == null) {
			return serverPort + "-" + "没有找到对应的session值";
		}
		System.out.println("获取Session sessionid:信息" + session.getId() + "serverPort:" + serverPort);
		Object value = session.getAttribute("name");
		return serverPort + "-" + value;
	}

}
