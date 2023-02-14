package com.fdmgroup.Confidential_secret_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepo;
	
	public double getValueOfCart(Integer cartId) {
		Optional<Cart> cart = findCartById(cartId);
		return cart.get().getValue();
	}

	public Optional<Cart> findCartById(Integer cartId) {
		Optional<Cart> cart= cartRepo.findById(cartId);
		return cart;
	}
	
	public Cart getCartFromDb() {
		List<Cart > list = cartRepo.findAll();
		return list.get(0);
		
		
	}

}
