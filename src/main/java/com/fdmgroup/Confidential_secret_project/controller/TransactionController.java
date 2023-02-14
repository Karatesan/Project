package com.fdmgroup.Confidential_secret_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Confidential_secret_project.service.CartService;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.TransactionService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class TransactionController {
	 
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CartService cartService;
	
	
	
	@PostMapping("/goToTransaction")
	public String getTransaction(ModelMap model, @ModelAttribute("couponId") Integer usedCouponId) {
			System.out.println("======= in go to transaction " + usedCouponId);
		
		if(!couponService.findById(usedCouponId).isPresent()) {
			model.addAttribute("errorMessage", "Coupon does not exist");
			}
		
		model.addAttribute("usedCoupon",couponService.findById(usedCouponId).get());
		model.addAttribute("oldCart",cartService.getCartFromDb().getValue());
		model.addAttribute("cart",cartService.getCartFromDb().getValue()-couponService.findById(usedCouponId).get().getTheValue());
		model.addAttribute("counter",couponService.findById(usedCouponId).get().getCounter()-1);
		//model.addAttribute("user",userService.findById(userId).get());
		//model.addAttribute("transaction",transactionService.creatingAndSavingTransaction(model,userId, cartService.getCartFromDb(), usedCouponId ))
		
		return "transaction";
	} 
	
	 }
	

  

