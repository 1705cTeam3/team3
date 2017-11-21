package com.jk.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/loginAction")
public class LoginController {

	@RequestMapping("loginIndex")
	private String loginIndex(){
		
		return null;
	}
	
}
