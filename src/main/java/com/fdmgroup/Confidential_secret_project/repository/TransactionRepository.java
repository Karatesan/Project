package com.fdmgroup.Confidential_secret_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.Confidential_secret_project.model.Transaction;
import com.fdmgroup.Confidential_secret_project.model.Customer;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByConsumer(Customer consumer);

}
