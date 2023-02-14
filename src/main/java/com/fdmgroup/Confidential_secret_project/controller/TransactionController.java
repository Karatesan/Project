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
	
		
	
	
	
	@PostMapping("/goToTransaction")
	public String getTransaction(ModelMap model, @ModelAttribute("couponId") Integer usedCouponId) {
			System.out.println("======= in go to transaction " + usedCouponId);
		
		if(!couponService.findById(usedCouponId).isPresent()) {
			model.addAttribute("errorMessage", "Coupon does not exist");
			}
		
		model.addAttribute("usedCoupon",couponService.findById(usedCouponId).get());
		//model.addAttribute("cart",cartService.getCartFromDb().getValue()-couponService.findById(usedCouponId).get().getTheValue());
		//model.addAttribute("user",userService.findById(userId).get());
		//model.addAttribute("transaction",transactionService.creatingAndSavingTransaction(model,userId, cartService.getCartFromDb(), usedCouponId ))
		
		return "transaction";
	} 
	
	
	@PostMapping("/transactionConfirmation")
	 public String confirmTransaction(ModelMap model,@RequestParam("confirmation") String confirmation, @ModelAttribute("cartId") Integer cartId, @ModelAttribute("userId") Integer userId, @ModelAttribute("couponId") Integer usedCouponId) {
		
		if(transactionService.checkObjectFortransaction(model, userId, cartId, usedCouponId)) {
			
			if(confirmation.equals("confirm")) {
				if(transactionService.checkObjectFortransaction(model, userId, cartId, usedCouponId)) {
					  ;
					  model.addAttribute("confirmation",confirmation);
				}
				
			}else {
				model.addAttribute("confirmation",confirmation);
			}
		}
		
		 return "transactionConfirmation";
	 }
	
	
	
	/*@GetMapping("/goToTransaction")
	 public String getTransaction(@RequestParam("transactionId") Integer transactionID, ModelMap model ) {
		 if(transactionService.findById(transactionID).isPresent()){	 
			 model.addAttribute("transaction",transactionService.findById(transactionID).get());
		 }else {
			 model.addAttribute("errorMessage", " transaction is not found");
		 }
		 
		 return "showTransaction";
	 }
	 
	 
	 
	 @PostMapping("/showTransaction")
	 public String postTransaction(ModelMap model, @RequestParam("cartId") Integer cartId, @RequestParam("userId") Integer userId, @RequestParam("couponId") Integer usedCouponId) {
		 Transaction transaction = transactionService.creatingAndSavingtransaction(userId, cartId, usedCouponId );
		  model.addAttribute("transaction",transaction);
		 
		 return "redirect:/goToTransaction";
	 }*/
	 
	 // czy musze ponownie sprawdzic wszystkie obiekty(user,cart,coupon) bo oni juz powinne być sprawdzane poprzednio przed oddaniem do transaction???
	// czy model.addAttribute("transaction",transaction) w post methodzie oddaje obiekt transaction w moethode get ktora otdaje strone z wyswietlaniem transakcji????
  
}
