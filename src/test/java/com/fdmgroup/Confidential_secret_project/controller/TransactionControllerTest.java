package com.fdmgroup.Confidential_secret_project.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

import com.fdmgroup.Confidential_secret_project.ConfidentialSecretProjectApplication;
import com.fdmgroup.Confidential_secret_project.controller.TransactionController;
import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Transaction;
import com.fdmgroup.Confidential_secret_project.model.Customer;
import com.fdmgroup.Confidential_secret_project.service.CartService;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.TransactionService;
import com.fdmgroup.Confidential_secret_project.service.UserService;


@SpringBootTest(classes = { TransactionController.class })
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = ConfidentialSecretProjectApplication.class)


public class TransactionControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;
	    
	    @MockBean
	    private TransactionService transactionService;
	    
	    @MockBean
	    private CartService cartService;
	    
	    @MockBean
	    private CouponService couponService;
	    
	    @MockBean
	    private UserService userService;
	    @MockBean
	    private MainController mainController;
	    
	 
	    
	    @Test
	    public void testCreateTransaction() throws Exception {
	        when(cartService.findCartById(1)).thenReturn(Optional.of(new Cart()));
	        when(userService.findById(1)).thenReturn(Optional.of(new Customer( "John", "Doe")));
	        when(couponService.findById(1)).thenReturn(Optional.of(new Coupon(2, 5, null)));
	        doNothing().when(mainController).fillData(any(ModelMap.class));
	        doNothing().when(transactionService).save(any(Transaction.class));
	        when(transactionService.findAllFromUser(null)).thenReturn(Collections.emptyList()); // add this line
	        
	        mockMvc.perform(post("/createTransaction")
	                .param("userId", "1")
	                .param("couponId", "1")
	                .param("cartId", "1"))
	            .andExpect(status().isOk())
	            .andExpect(view().name("index"))
	            .andExpect(model().attribute("message", "Transaction completed"));
	    }

	    
	    @Test
	    public void testShowAllUserTransactions() throws Exception {
	        Customer user = new Customer("test", "tesst");
	        Transaction transaction = new Transaction();
	        transaction.setConsumer(user);
	        transaction.setCart(new Cart(20,"name"));
	        transaction.setConsumer(user);
	        List<Transaction> transactions = new ArrayList<>();
	        transactions.add(transaction);
	        
	        when(userService.findById(1)).thenReturn(Optional.of(user));
	        when(transactionService.findAllFromUser(user)).thenReturn(transactions);
	        doNothing().when(mainController).fillData(any(ModelMap.class));
	        
	        mockMvc.perform(get("/showAllUserTransactions")
	                .param("userId", "1"))
	            .andExpect(status().isOk())
	            .andExpect(view().name("index"))
	            .andExpect(model().attribute("usersTransaction", transactions));
	    }
	}


