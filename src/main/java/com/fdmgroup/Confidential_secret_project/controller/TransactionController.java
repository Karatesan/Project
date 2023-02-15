package com.fdmgroup.Confidential_secret_project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Transaction;
import com.fdmgroup.Confidential_secret_project.model.Customer;
import com.fdmgroup.Confidential_secret_project.service.CartService;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.TransactionService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	@Autowired
	CartService cartService;
	@Autowired
	CouponService couponService;
	@Autowired
	UserService userService;
	@Autowired
	MainController mainController;
	
	@PostMapping("/createTransaction")
	public String createTransaction(ModelMap model, @RequestParam String userId, @RequestParam String couponId, @RequestParam String cartId)
	{
		Optional<Cart> cart = cartService.findCartById(Integer.parseInt(cartId));
		Optional<Customer> pickedUser = userService.findById(Integer.parseInt(userId));
		Optional<Coupon> pickedCoupon = couponService.findById(Integer.parseInt(couponId));
		pickedCoupon.get().setCounter(pickedCoupon.get().getCounter()-1);
		if(pickedCoupon.get().getCounter()==0) pickedCoupon.get().setStatus(false);
		
		Transaction transaction = new Transaction(pickedUser.get(), cart.get(), pickedCoupon.get());
		transactionService.save(transaction);
		mainController.fillData(model);
		model.addAttribute("message", "Transaction completed");
		
		return "index";
	}

	@GetMapping("/showAllUserTransactions")
	public String showAllUserTransactions(ModelMap model, @RequestParam String userId) {
		
		Optional<Customer> user = userService.findById(Integer.parseInt(userId));
		if(user.isEmpty()) 
			model.addAttribute("errorMessage", "Wrong user id");
		else {
			model.addAttribute("usersTransaction", transactionService.findAllFromUser(user.get()));
		}		
		mainController.fillData(model);
		return "index";
	}
}
