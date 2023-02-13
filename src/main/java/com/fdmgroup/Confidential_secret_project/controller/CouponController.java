package com.fdmgroup.Confidential_secret_project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.service.CouponService;

@Controller
public class CouponController {
	
	
	@Autowired
	CouponService couponService;
	
	
	
	@GetMapping("/pickCoupon")
	public String pickCoupon(ModelMap model, @RequestParam Integer couponId) {
		
		Optional<Coupon> pickedCoupon = couponService.findById(couponId);
		
		//sprawdzam czy kupon jest w bazie, potem czy jest aktywny i go przesylam dalej albo daje odpowiednie errory
		
		if(pickedCoupon.isPresent()) { 
			if(pickedCoupon.get().isStatus()) {
			model.addAttribute("pickedCoupon", pickedCoupon);
		}
			else {
				model.addAttribute("errorStatus", "Coupon is already used");
			}
		}
			else {
				model.addAttribute("errorCoupon", "No coupon with id: " + couponId);
			}
		
		return "index";
	}
	

}
