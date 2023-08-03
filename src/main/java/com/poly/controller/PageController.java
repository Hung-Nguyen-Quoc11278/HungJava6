package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/home/404")
	public String error() {	
		return "user/blog/404";
	}
	
	@RequestMapping("/home/contact")
	public String checkout() {
		return "user/blog/contact";
	}
	
	@RequestMapping("/home/blog1")
	public String blog1() {
		return "user/blog/blog-archive";
	}

	@RequestMapping("/home/blog2")
	public String blog2() {
		return "user/blog/blog-single";
	}
}
