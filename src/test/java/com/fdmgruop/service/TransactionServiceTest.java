package com.fdmgruop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}
