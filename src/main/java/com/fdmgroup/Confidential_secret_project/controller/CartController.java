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
import com.fdmgroup.Confidential_secret_project.service.CartService;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class CartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/checkUserId")
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
	
	@GetMapping("/setCartValue")
	public void checkValueOfCart(Model model, @PathVariable Integer couponId) {
		double cartValue = cartService.getValueOfCart(couponId);	// get value of current cart
		Optional<Coupon> pickedCoupon = couponService.findById(couponId); // check what coupon was used
		if(pickedCoupon.isPresent()) {	// if above coupon exists, reduce value of cart by value of chose coupon
			double couponValue = pickedCoupon.get().getTheValue();
			double currentValue= cartValue - couponValue;
			model.addAttribute("currentValueOfCart", currentValue);
		}
		else {	//if coupon doesn't exist, show value of cart unchanged
			model.addAttribute("currentValueOfCart", cartValue);
		}
	}

}
