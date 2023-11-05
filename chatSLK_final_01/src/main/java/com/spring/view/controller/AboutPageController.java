package com.spring.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutPageController {
	
	@RequestMapping(value="/aboutPage.do")
	public String aboutPage() {
		System.out.println("aboutPageController로그");
		
		return "about.jsp";
	}
	
}
