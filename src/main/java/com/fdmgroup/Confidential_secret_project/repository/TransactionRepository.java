package com.fdmgroup.Confidential_secret_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Confidential_secret_project.model.Transaction;
import com.fdmgroup.Confidential_secret_project.model.Users;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
		List<Transaction> findAllByConsumer(Users consumer);
}
