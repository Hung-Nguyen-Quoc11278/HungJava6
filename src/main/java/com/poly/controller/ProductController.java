package com.poly.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.services.ProductService;
import com.poly.utils.SessionService;

@Controller
public class ProductController {
	
	@Autowired
	ProductDAO dao;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SessionService session;
	
	@RequestMapping("/product/list")
	public String list(Model model,
			@RequestParam("p") Optional<Integer> p,
			@RequestParam("keywords") Optional<String> kw) {
		String keywords = kw.orElse(session.get("keywords", ""));
		session.set("keywords", keywords);
		Pageable pageable = PageRequest.of(p.orElse(0), 12);
		Page<Product> page = dao.findAllByNameLike("%"+keywords+"%", pageable);
		model.addAttribute("page", page);
		
//		List<Product> list = productService.findAll();
//		model.addAttribute("items" , list);
		return "user/product/product";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item" , item);
		return "user/product/product-detail";
	}
	
	@RequestMapping("/product/quickview/{id}")
	public String quickview(Model model, @PathVariable("id") Integer id) {
		Product item1 = productService.findById(id);
		model.addAttribute("item1" , item1);
		return "user/product/product";
	}


}
