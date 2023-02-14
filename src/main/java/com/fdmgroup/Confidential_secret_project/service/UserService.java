package com.fdmgroup.Confidential_secret_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public Optional<Users> findById(Integer userId) {
		Optional<Users> opt = userRepo.findById(userId);
		return opt;
	}
	
	public List<Users> findAllUsers(){
		return userRepo.findAll();
	}
}
