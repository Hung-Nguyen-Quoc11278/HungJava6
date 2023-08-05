package com.poly.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.services.OAuth2Service;


@Controller
public class SecurityController {
	
	@RequestMapping("/security/login/form")
	public String form() {
		return "user/account/login";

	}

	@RequestMapping("/security/login/success")
	public String success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công !");
		return "forward:/security/login/form";

	}

	@RequestMapping("/security/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai Thông Tin đăng nhập !");
		return "forward:/security/login/form";

	}

	@RequestMapping("/security/logoff/success")
	public String logoff(Model model) {
		model.addAttribute("message", "Đăng Xuất Thành Công !");
		return "forward:/security/login/form";

	}

	@RequestMapping("/security/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "Bạn Không Có Quyền Truy Cập !");
		return "user/account/login";

	}
	
	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/security/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
	
		// OAuth2
		@Autowired
		OAuth2Service oauth2Service;
		
		@RequestMapping("/oauth2/login/success")
		public String success(OAuth2AuthenticationToken oauth2) {
			oauth2Service.loginFormOAuth2(oauth2);
			return "forward:/security/login/success";
		}
		
		

}
