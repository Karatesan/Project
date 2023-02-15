package com.fdmgroup.Confidential_secret_project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.repository.CartRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CartServiceTest {
	

    @InjectMocks
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @Test
    public void testFindCartById() {
        // Set up test data
        int cartId = 1;
        Cart expectedCart = new Cart();
        expectedCart.setCartId(cartId);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(expectedCart));

        // Call the method under test
        Optional<Cart> actualCart = cartService.findCartById(cartId);

        // Assert the result
        assertTrue(actualCart.isPresent());
        assertEquals(expectedCart, actualCart.get());
    }

    @Test
    public void testGetValueOfCart() {
        // Set up test data
        int cartId = 1;
        double expectedValue = 10.0;
        Cart cart = new Cart();
        cart.setValue(expectedValue);
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        // Call the method under test
        double actualValue = cartService.getValueOfCart(cartId);

        // Assert the result
        assertEquals(expectedValue, actualValue, 0.0);
    }

    @Test
    public void testFindAllList() {
        // Set up test data
        List<Cart> expectedCarts = new ArrayList<>();
        expectedCarts.add(new Cart());
        expectedCarts.add(new Cart());
        when(cartRepository.findAll()).thenReturn(expectedCarts);

        // Call the method under test
        List<Cart> actualCarts = cartService.findAllList();

        // Assert the result
        assertEquals(expectedCarts.size(), actualCarts.size());
        assertEquals(expectedCarts.get(0), actualCarts.get(0));
        assertEquals(expectedCarts.get(1), actualCarts.get(1));
    }
}


