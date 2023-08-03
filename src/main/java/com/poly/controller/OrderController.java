package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
	
	@RequestMapping("/order/checkout")
	public String checkout() {
		
		return "user/cart/checkout";
	}
	
	@RequestMapping("/order/list")
	public String list() {
		
		return "user/product";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail() {
		
		return "user/product";
	}

}
