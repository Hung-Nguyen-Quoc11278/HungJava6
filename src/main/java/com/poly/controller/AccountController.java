package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
	
	//	Change Pass
	@RequestMapping("/home/changepassword")
	public String changepass() {
		return "user/account/changepass";
	}
	
	
	//	Register
	@RequestMapping("/home/register")
	public String register() {
		return "user/account/register";
	}
	
	

}
