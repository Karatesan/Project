package com.fdmgroup.Confidential_secret_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CouponService couponService;
		
	@GetMapping("/")
	public String pickUserId(ModelMap model) {
		model.addAttribute("usersFromDB", userService.findAllUsers());
		return "user";
	}
	
	@PostMapping("/submitUserThenCoupon")
	public String submitUserId(ModelMap model, @RequestParam Integer userId) {
		Optional<Users> thisUser = userService.findById(userId);
		model.addAttribute("choseUserId", thisUser);
		List<Coupon> usersCoupons = couponService.findByOwnerId(userId);
		for(Coupon coupon: usersCoupons) {
			System.out.println(coupon);
		}
		model.addAttribute("thisUserCoupons", usersCoupons);
		return "coupon";
	}

}
