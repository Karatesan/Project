package com.fdmgroup.Confidential_secret_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.repository.CouponRepository;

import ch.qos.logback.core.joran.conditional.IfAction;

@Service
public class CouponService {
	
	@Autowired
	private CouponRepository couponRepo;
	
	@Autowired
	private UserService userService;
	
	public void save(Coupon coupon) {
		couponRepo.save(coupon);	
	}
	
	public List<Coupon> findByOwnerId(Integer ownerId) {
		Optional<Users> owner = userService.findById(ownerId);
		if(owner.isPresent())  
			return couponRepo.findByOwner(owner.get());
		else {
			return null;
		}	
	}	
	
	public void updateCoupon(Coupon coupon) {
		save(coupon);	
	}

	public Optional<Coupon> findById(Integer couponId) {
		Optional<Coupon> opt = couponRepo.findById(couponId);
		return opt;
	}
	
	public List<Coupon> findAllCoupons(){
		return couponRepo.findAll();
	}
}

	
	
	

