package com.fdmgroup.Confidential_secret_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdmgroup.Confidential_secret_project.model.Customer;



public interface UserRepository extends JpaRepository<Customer, Integer> {
	

}
