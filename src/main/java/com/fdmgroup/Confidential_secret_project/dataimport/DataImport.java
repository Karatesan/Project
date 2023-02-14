package com.fdmgroup.Confidential_secret_project.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.repository.CouponRepository;
import com.fdmgroup.Confidential_secret_project.repository.UserRepository;

@Component
public class DataImport implements ApplicationRunner {
	
	private UserRepository userRepository;
	private CouponRepository couponRepository;
	
	@Autowired
	public DataImport(UserRepository userRepository, CouponRepository couponRepository) {
		this.userRepository = userRepository;
		this.couponRepository = couponRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//Cart testCart = new Cart(1000, "Test Cart");
		Users customer = new Users("Raul", "Gonzales");
		Coupon coupon1 = new Coupon(5, 5, customer);
		Coupon coupon2 = new Coupon(5, 7, customer);
		Coupon coupon3 = new Coupon(5, 15, customer);
		
		
		
		Users customer2 = new Users("Ricardo", "Mora");
		Coupon coupon4 = new Coupon(5, 5.50, customer);
		Coupon coupon5 = new Coupon(5, 7.90, customer);
		Coupon coupon6 = new Coupon(5, 15.30, customer);
		
		userRepository.save(customer);
		userRepository.save(customer2);
		couponRepository.save(coupon1);
		couponRepository.save(coupon2);
		couponRepository.save(coupon3);
		couponRepository.save(coupon4);
		couponRepository.save(coupon5);
		couponRepository.save(coupon6);
	}



}
