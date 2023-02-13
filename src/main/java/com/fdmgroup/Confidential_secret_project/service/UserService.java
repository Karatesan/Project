package com.fdmgroup.Confidential_secret_project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Confidential_secret_project.model.User;
import com.fdmgroup.Confidential_secret_project.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public Optional<User> findById(Integer userId) {
		Optional<User> opt = userRepo.findById(userId);
		return opt;
	}

}
