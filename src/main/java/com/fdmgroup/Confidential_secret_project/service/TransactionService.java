package com.fdmgroup.Confidential_secret_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.Confidential_secret_project.model.Transaction;
import com.fdmgroup.Confidential_secret_project.model.Customer;
import com.fdmgroup.Confidential_secret_project.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository repo;

	public void save(Transaction transaction) {
		repo.save(transaction);	
	}

	public List<Transaction> findAllFromUser(Customer customer) {
		return repo.findByConsumer(customer);
	}
	

}
