package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {
	
	@RequestMapping("/admin/index")
	public String ad() {
		return "admin/ADindex";
	}

	@RequestMapping("/admin/home/index")
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
}
	
