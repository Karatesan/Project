package com.fdmgroup.Confidential_secret_project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
		
	@GetMapping("/")
	public String pickUserId(ModelMap model, @PathVariable Integer userId) {
		model.addAttribute("usersFromDB", userService.findAllUsers());
		return "index";
	}

}
