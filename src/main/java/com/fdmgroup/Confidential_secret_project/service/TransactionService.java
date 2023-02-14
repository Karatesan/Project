package com.fdmgroup.Confidential_secret_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.ModelMap;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Transaction;
import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.repository.TransactionRepository;


@Service
public class TransactionService {
	
	@Autowired 
	private  TransactionRepository repository;
	@Autowired
	private CouponService couponService;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	
	
	public void saveTransaction(Transaction transaction) {
		repository.save(transaction);
	}
	
	public void deletetransaction(Transaction transaction ) {
		repository.delete(transaction);
	}
	
	
	public List<Transaction> findAlltransactionsByUser(Users consumer){
		return repository.findAllByConsumer(consumer);
	}
	
	
	public Optional<Transaction> findById(Integer id) {
		return repository.findById(id);
	}
	
	
	public Transaction creatingAndSavingTransaction( ModelMap model,Integer userId,Integer cartId, Integer usedCouponId) {
		
		Transaction transaction = new Transaction(userService.findById(userId).get(),cartService.findById(cartId).get(),couponService.findById(usedCouponId).get());
		saveTransaction(transaction);
		
		/// na moment tworzenia transakcji to counter couponu jest taki jak był w moment uzycia a potem juz --;
		Integer couponCounter = couponService.findById(usedCouponId).get().getCounter();
		couponService.findById(usedCouponId).get().setCounter(couponCounter--);
		couponService.save(couponService.findById(usedCouponId).get());
		
		return transaction;
		
	}
	
	
	public boolean checkObjectFortransaction(ModelMap model,Integer userId,Integer cartId, Integer usedCouponId) {
		if(!cartService.findById(cartId).isPresent()) {
			model.addAttribute("errorMessage", "Cart does not exist");
			return false;
			}
			if(!userService.findById(userId).isPresent()) {
				model.addAttribute("errorMessage", "User does not exist");
				return false;
				}	
			if(!couponService.findById(usedCouponId).isPresent()) {
				model.addAttribute("errorMessage", "Coupon does not exist");
				return false;
				}
			
			return true;
	}

}
