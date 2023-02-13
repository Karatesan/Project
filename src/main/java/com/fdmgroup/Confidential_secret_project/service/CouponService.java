package com.fdmgroup.Confidential_secret_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.User;
import com.fdmgroup.Confidential_secret_project.repository.CouponRepository;

@Service
public class CouponService {
	
	@Autowired
	CouponRepository couponRepo;
	
	@Autowired
	UserService userService;
	
	public void save(Coupon coupon) {
		couponRepo.save(coupon);	
	}

	public List<Coupon> findByOwnerId(Integer ownerId) {
		Optional<User> owner = userService.findById(ownerId);
		if(owner.isEmpty())  
			return couponRepo.findByOwner(owner.get());
		else {
			return null;
		}
		
	}
}
