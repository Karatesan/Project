package com.fdmgroup.Confidential_secret_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
		
	@GetMapping("/")
	public String goToIndex() {
		return "index";
	}

}
