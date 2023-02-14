package com.fdmgroup.Confidential_secret_project.controller;

import java.util.ArrayList;
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
import com.fdmgroup.Confidential_secret_project.service.CartService;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CouponService couponService;
	@Autowired 
	private CartService cartService;
		
	@GetMapping("/")
	public String pickUserId(ModelMap model) {
		model.addAttribute("usersFromDB", userService.findAllUsers());
		return "users";
	}
	
	//shoud be named the cart and list of coupons 
	@PostMapping("/submitUserThenCoupon")
	public String submitUserId(ModelMap model, @RequestParam Integer userId) {
		//Optional<Users> thisUser = userService.findById(userId);
		
		Users user = userService.findById(userId).get();
		//model.addAttribute("choseUserId", thisUser);
		model.addAttribute("user", user);
		List<Coupon> usersCoupons = couponService.findByOwnerId(userId);
		List<Coupon> activeCoupons = new ArrayList<>();
		
		for(Coupon coupon: usersCoupons) {
			if(coupon.getCounter()>0) {
				activeCoupons.add(coupon);
			}
		}
		model.addAttribute("thisUserCoupons", activeCoupons);
		model.addAttribute("cart",cartService.getCartFromDb().getValue());
		
		
		return "coupon";
	}

}
