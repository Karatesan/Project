package com.fdmgroup.Confidential_secret_project.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.Confidential_secret_project.ConfidentialSecretProjectApplication;
import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Customer;
import com.fdmgroup.Confidential_secret_project.service.CartService;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

@SpringBootTest(classes = { CartController.class })
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = ConfidentialSecretProjectApplication.class)

public class CartControllerTest {
	
		@Autowired
	   private MockMvc mockMvc;

	   @MockBean
	    private CouponService couponService;

	   @MockBean
	    private UserService userService;

	   @MockBean
	    private CartService cartService;
	   @MockBean
	    private MainController mainController;


	    @Test
	    public void testConfirmTransaction() throws Exception {
	        // Given
	        int userId = 1;
	        int couponId = 1;
	        int cartId = 1;
	        Optional<Customer> user = Optional.of(new Customer());
	        user.get().setUserId(userId);
	        Optional<Coupon> coupon = Optional.of(new Coupon());
	        coupon.get().setCouponId(couponId);
	        coupon.get().setOwner(user.get());
	        coupon.get().setStatus(true);
	        Optional<Cart> cart = Optional.of(new Cart());
	        cart.get().setCartId(cartId);
	        cart.get().setTheValue(50);
	        when(userService.findById(userId)).thenReturn(user);
	        when(couponService.findById(couponId)).thenReturn(coupon);
	        when(cartService.findCartById(cartId)).thenReturn(cart);

	        // When and Then
	        mockMvc.perform(get("/confirmTransaction")
	                .param("userId", Integer.toString(userId))
	                .param("couponId", Integer.toString(couponId))
	                .param("cartId", Integer.toString(cartId)))
	               .andExpect(status().isOk())
	               .andExpect(view().name("confirm"))
	               .andExpect(model().attributeExists("user"))
	               .andExpect(model().attributeExists("coupon"))
	               .andExpect(model().attributeExists("cart"))
	               .andExpect(model().attributeDoesNotExist("errorMessage"));

	        // Verify cart value updated
	        assert(cart.get().getTheValue() == 50);
	    }
	    
	    @Test
	    public void testConfirmTransactionReturnsUserErrorWhenUserDoesNotExist() throws Exception {
	        // Given
	        int userId = 1;
	        int couponId = 1;
	        int cartId = 1;
	        Optional<Customer> user = Optional.of(new Customer());
	        user.get().setUserId(userId);
	        Optional<Coupon> coupon = Optional.of(new Coupon());
	        coupon.get().setCouponId(couponId);
	        Optional<Cart> cart = Optional.of(new Cart());
	        cart.get().setCartId(cartId);
	        when(userService.findById(userId)).thenReturn(Optional.ofNullable(null));
	        when(couponService.findById(couponId)).thenReturn(coupon);
	        when(cartService.findCartById(cartId)).thenReturn(cart);

	        // When and Then
	        mockMvc.perform(get("/confirmTransaction")
	                .param("userId", Integer.toString(userId))
	                .param("couponId", Integer.toString(couponId))
	                .param("cartId", Integer.toString(cartId)))
	               .andExpect(status().isOk())
	               .andExpect(view().name("index"))
	               .andExpect(model().attributeExists("errorMessage"));

	    }
	    
	    @Test
	    public void testConfirmTransactionReturnsCouponErrorWhenCouponDoesNotExist() throws Exception {
	        // Given
	        int userId = 1;
	        int couponId = 1;
	        int cartId = 1;
	        Optional<Customer> user = Optional.of(new Customer());
	        user.get().setUserId(userId);
	        Optional<Coupon> coupon = Optional.of(new Coupon());
	        coupon.get().setCouponId(couponId);
	        Optional<Cart> cart = Optional.of(new Cart());
	        cart.get().setCartId(cartId);
	        when(userService.findById(userId)).thenReturn(user);
	        when(couponService.findById(couponId)).thenReturn(Optional.ofNullable(null));
	        when(cartService.findCartById(cartId)).thenReturn(cart);

	        // When and Then
	        mockMvc.perform(get("/confirmTransaction")
	                .param("userId", Integer.toString(userId))
	                .param("couponId", Integer.toString(couponId))
	                .param("cartId", Integer.toString(cartId)))
	               .andExpect(status().isOk())
	               .andExpect(view().name("index"))
	               .andExpect(model().attributeExists("errorMessage"));

	    }
	    
	    @Test
	    public void testConfirmTransactionReturnsActiveErrorWhenCouponIsNotActive() throws Exception {
	        // Given
	        int userId = 1;
	        int couponId = 1;
	        int cartId = 1;
	        Optional<Customer> user = Optional.of(new Customer("test","testtez"));
	        user.get().setUserId(userId);
	        Optional<Coupon> coupon = Optional.of(new Coupon());
	        coupon.get().setCouponId(couponId);
	        coupon.get().setStatus(false);
	        coupon.get().setOwner(user.get());
	        Optional<Cart> cart = Optional.of(new Cart());
	        cart.get().setCartId(cartId);
	        when(userService.findById(userId)).thenReturn(user);
	        when(couponService.findById(couponId)).thenReturn(coupon);
	        when(cartService.findCartById(cartId)).thenReturn(cart);

	        // When and Then
	        mockMvc.perform(get("/confirmTransaction")
	                .param("userId", Integer.toString(userId))
	                .param("couponId", Integer.toString(couponId))
	                .param("cartId", Integer.toString(cartId)))
	               .andExpect(status().isOk())
	               .andExpect(view().name("index"))
	               .andExpect(model().attributeExists("errorMessage"));

	    }
	}

