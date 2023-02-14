package com.fdmgroup.Confidential_secret_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
		
	@GetMapping("/")
	public String getAllUsersById() {
		return "index";
	}

}
