package com.fdmgroup.Confidential_secret_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Customer;
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
	
	
	
	@GetMapping("/confirmTransaction")
	public String confirmTransaction(ModelMap model, @RequestParam String userId, @RequestParam String couponId, @RequestParam String cartId) {
		
		Integer userInt = Integer.parseInt(userId);
		Integer couponInt = Integer.parseInt(couponId);
		Optional<Cart> cart = cartService.findCartById(Integer.parseInt(cartId));
		
		Optional<Customer> pickedUser = userService.findById(userInt);
		if (pickedUser.isPresent()) { 
			Optional<Coupon> pickedCoupon = couponService.findById(couponInt);
			if(pickedCoupon.isPresent()) {		
				if(pickedCoupon.get().getOwner().equals(pickedUser.get())) {
					if(pickedCoupon.get().isStatus()) {
					model.addAttribute("coupon",pickedCoupon.get());
					model.addAttribute("user",pickedUser.get());
					cart.get().setTheValue(cart.get().getTheValue()-pickedCoupon.get().getTheValue());
					model.addAttribute("cart",cart.get());
					return "confirm";
				}
				else 
					model.addAttribute("errorMessage", "Coupon is inactive");	
				}
				else 
					model.addAttribute("errorMessage", "Coupon is owned by diffrent user");
			}
			else 
				model.addAttribute("errorMessage", "Wrong coupon id");
		}
		else 
			model.addAttribute("errorMessage", "Wrong user id");
				
		return "index";		
		}
			

	
	
	
	
	}


