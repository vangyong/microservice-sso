package cn.segema.cloud.sso.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}

}
