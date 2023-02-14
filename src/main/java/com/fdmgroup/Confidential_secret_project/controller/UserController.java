package com.fdmgroup.Confidential_secret_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository	userRepo;

	public List<Users> getAllUsersById(Integer userId){
		return userRepo.findAllById(userId);
		
	}
}
