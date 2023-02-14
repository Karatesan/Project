package com.fdmgroup.Confidential_secret_project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.service.CartService;
import com.fdmgroup.Confidential_secret_project.service.CouponService;

@Controller
public class CartController {
		
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CartService cartService;
	
	
	@GetMapping("/setCartValue")
	public String checkValueOfCart(ModelMap model, @PathVariable Integer couponId) {
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
		return "coupon";
	}

}
