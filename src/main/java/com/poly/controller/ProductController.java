package com.poly.controller;

import java.util.List;
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
					@RequestParam("cid") Optional<String> cid,
					@RequestParam("keywords") Optional<String> kw,
					@RequestParam("p") Optional<Integer> p,
					@RequestParam("field") Optional<String> field) {
		
		List<Product> list4 = productService.findBySellPro();
		model.addAttribute("list4", list4);
		
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("page", list);
		} else {
			String keywords = kw.orElse(session.get("keywords", ""));
			session.set("keywords", keywords);
			Pageable pageable = PageRequest.of(p.orElse(0),9);
			Page<Product> page = dao.findAllByNameLike("%"+keywords+"%", pageable);
			model.addAttribute("page", page);
		}
				
		return "user/product/product";
	}
	
	@RequestMapping("/product/list/sort")
	public String list2(Model model,
				@RequestParam("keywords") Optional<String> kw,
				@RequestParam("p") Optional<Integer> p,
				@RequestParam("field") String field) {
		
		List<Product> list4 = productService.findBySellPro();
		model.addAttribute("list4", list4);
		
			Pageable pageable = PageRequest.of(p.orElse(0),9);
			Page<Product> page = dao.findByField(field, pageable);
			model.addAttribute("page", page);
				
		return "user/product/product";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "user/product/product-detail";
	}
	
	@RequestMapping("/admin/product")
	public String pro(Model model) {
		return "admin/ADproduct";
	}

}
