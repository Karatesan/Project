package com.fdmgroup.Confidential_secret_project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Customer;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class CouponController {
	
	
	@Autowired
	CouponService couponService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MainController mainController;
	
	
	@GetMapping("/showAllUserCoupons")
	public String getAllCouponsFromUser(ModelMap model, @RequestParam String userId) {
		
		Optional<Customer> user = userService.findById(Integer.parseInt(userId));
		if(user.isEmpty()) 
			model.addAttribute("errorMessage", "Wrong user id");
		else {
			model.addAttribute("usersCoupons", couponService.findByOwnerId(Integer.parseInt(userId)));
			model.addAttribute("callingUser", user.get());
		}
		mainController.fillData(model);
		
		return "index";	
	}
	

}
