package com.fdmgroup.Confidential_secret_project.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.repository.CartRepository;
import com.fdmgroup.Confidential_secret_project.repository.CouponRepository;
import com.fdmgroup.Confidential_secret_project.repository.UserRepository;

@Component
public class DataImport implements ApplicationRunner {
	
	private UserRepository userRepository;
	private CouponRepository couponRepository;
	private CartRepository cartRepository;
	
	@Autowired
	public DataImport(UserRepository userRepository, CouponRepository couponRepository, CartRepository cartRepository) {
		this.userRepository = userRepository;
		this.couponRepository = couponRepository;
		this.cartRepository = cartRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Users customer1 = new Users("Jan", "Nowak");
		Users customer2 = new Users("Niejan", "Nowak");
		
		userRepository.save(customer1);
		userRepository.save(customer2);
				
		Coupon coupon1 = new Coupon(2, 5, customer1);
		Coupon coupon2 = new Coupon(3, 7, customer1);
		Coupon coupon3 = new Coupon(4, 15, customer1);
		
		Coupon coupon4 = new Coupon(3, 5.50, customer2);
		Coupon coupon5 = new Coupon(2, 7.90, customer2);
		Coupon coupon6 = new Coupon(1, 15.30, customer2);
		
		couponRepository.save(coupon1);
		couponRepository.save(coupon2);
		couponRepository.save(coupon3);
		couponRepository.save(coupon4);
		couponRepository.save(coupon5);
		couponRepository.save(coupon6);
		
		Cart cart1 = new Cart(100.50);
		
		cartRepository.save(cart1);

		
		
	}



}
