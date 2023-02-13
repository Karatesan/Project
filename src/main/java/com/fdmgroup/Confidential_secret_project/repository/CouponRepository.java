package com.fdmgroup.Confidential_secret_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.User;


public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
	public List<Coupon>findByOwner(User owner);
	
}
