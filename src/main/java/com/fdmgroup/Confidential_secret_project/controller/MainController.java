package com.fdmgroup.Confidential_secret_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.service.CartService;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;	
	@Autowired
	CouponService couponService;
	@Autowired
	CartService cartService;
	
	@GetMapping(value="/")
	public String goToIndex(ModelMap model) {
		
		fillData(model);
		return "index";
	}
	
	public void fillData(ModelMap model) {
		model.addAttribute("userList", userService.findAllList());
		model.addAttribute("couponList", couponService.findAllCoupons());
		Cart cart = cartService.findAllList().get(0);
		model.addAttribute("cart", cart);
		
	}
}
