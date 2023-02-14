package com.fdmgroup.Confidential_secret_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdmgroup.Confidential_secret_project.model.Users;



public interface UserRepository extends JpaRepository<Users, Integer> {

		List<Users> findAllById(Integer userId);
	

}
