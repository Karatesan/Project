package com.fdmgruop.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ModelMap;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Transaction;
import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.repository.TransactionRepository;
import com.fdmgroup.Confidential_secret_project.service.CartService;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.TransactionService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CouponService couponService;

    @Mock
    private CartService cartService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void testSaveTransaction() {
        Transaction transaction = new Transaction();
        transactionService.saveTransaction(transaction);
        verify(transactionRepository).save(transaction);
    }

    @Test
    public void testDeleteTransaction() {
        Transaction transaction = new Transaction();
        transactionService.deleteTransaction(transaction);
        verify(transactionRepository).delete(transaction);
    }

    @Test
    public void testFindAlltransactionsByUser() {
        Users user = new Users();
        List<Transaction> expectedTransactions = new ArrayList<>();
        when(transactionRepository.findAllByConsumer(user)).thenReturn(expectedTransactions);

        List<Transaction> actualTransactions = transactionService.findAlltransactionsByUser(user);

        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void testFindById() {
        Transaction expectedTransaction = new Transaction();
        when(transactionRepository.findById(1)).thenReturn(Optional.of(expectedTransaction));

        Optional<Transaction> actualTransaction = transactionService.findById(1);

        assertTrue(actualTransaction.isPresent());
        assertEquals(expectedTransaction, actualTransaction.get());
    }
    

	@Test
	public void testCreatingAndSavingTransaction() {
		Users user = new Users();
		user.setUserId(1);
		Cart cart = new Cart();
		cart.setCartId(1);
		Coupon coupon = new Coupon();
		coupon.setCouponId(1);
		coupon.setCounter(5);

		when(userService.findById(1)).thenReturn(Optional.of(user));
		when(cartService.findCartById(1)).thenReturn(Optional.of(cart));
		when(couponService.findById(1)).thenReturn(Optional.of(coupon));
		when(couponService.save(coupon)).thenReturn(coupon);

		Transaction transaction = transactionService.creatingAndSavingTransaction(new ModelMap(), 1, cart, 1);

		assertEquals(user, transaction.getConsumer());
		assertEquals(cart, transaction.getCart());
		assertEquals(coupon, transaction.getCouponUsed());
		verify(transactionRepository, times(1)).save(transaction);
		verify(couponService, times(1)).save(coupon);
		assertEquals(4, coupon.getCounter());
	}
	
	@Test
	public void testCheckObjectForTransactionAllObjectsExist() {
		Users user = new Users();
		user.setUserId(1);
		Cart cart = new Cart();
		cart.setCartId(1);
		Coupon coupon = new Coupon();
		coupon.setCouponId(1);

		when(userService.findById(1)).thenReturn(Optional.of(user));
		when(cartService.findCartById(1)).thenReturn(Optional.of(cart));
		when(couponService.findById(1)).thenReturn(Optional.of(coupon));

		assertTrue(transactionService.checkObjectFortransaction(new ModelMap(), 1, 1, 1));
	}

	@Test
	public void testCheckObjectForTransactionCartDoesNotExist() {
		Users user = new Users();
		user.setUserId(1);
		Coupon coupon = new Coupon();
		coupon.setCouponId(1);

		when(userService.findById(1)).thenReturn(Optional.of(user));
		when(cartService.findCartById(1)).thenReturn(Optional.empty());
		when(couponService.findById(1)).thenReturn(Optional.of(coupon));

		assertFalse(transactionService.checkObjectFortransaction(new ModelMap(), 1, 1, 1));
	}

	@Test
	public void testCheckObjectForTransactionUserDoesNotExist() {
		Cart cart = new Cart();
		cart.setCartId(1);
		Coupon coupon = new Coupon();
		coupon.setCouponId(1);

		when(userService.findById(1)).thenReturn(Optional.empty());
		when(cartService.findCartById(1)).thenReturn(Optional.of(cart));
		when(couponService.findById(1)).thenReturn(Optional.of(coupon));

		assertFalse(transactionService.checkObjectFortransaction(new ModelMap(), 1, 1, 1));
	}
}
