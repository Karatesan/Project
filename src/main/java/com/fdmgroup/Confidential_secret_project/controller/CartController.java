package com.fdmgroup.Confidential_secret_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class CartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CouponService couponService;
	
	@GetMapping("/")
	public String checkUserId(Model model, @PathVariable Integer userId) {
		Optional<Users> idToCheckWith = userService.findById(userId); // check if userId entered at page exists in our Database
		if (idToCheckWith.isPresent()) { //if userId correct, get all coupons which it possess
			List<Coupon> ownedByUserCoupons = couponService.findByOwnerId(userId);
			model.addAttribute("ownedByUserCoupons", ownedByUserCoupons);	// pass list of coupons to webpage	
		}
		else {
			model.addAttribute("errorUserId", "UserID doen't exist");	//if entered userId incorrect- error
		}
		return "index";
	}

}
