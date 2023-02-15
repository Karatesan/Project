package com.fdmgruop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.Confidential_secret_project.model.Cart;
import com.fdmgroup.Confidential_secret_project.repository.CartRepository;
import com.fdmgroup.Confidential_secret_project.service.CartService;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void testGetValueOfCartReturnsValueWhenCartExists() {
        // Arrange
        Integer cartId = 1;
        double expectedValue = 10.0;
        Cart cart = new Cart();
        cart.setCartId(cartId);
        cart.setValue(expectedValue);
        Optional<Cart> expectedCart = Optional.of(cart);
        Mockito.when(cartRepository.findById(cartId)).thenReturn(expectedCart);

        // Act
        double actualValue = cartService.getValueOfCart(cartId);

        // Assert
        assertEquals(expectedValue, actualValue, 0.01);
    }

    @Test
    public void testGetValueOfCartThrowsExceptionWhenCartDoesNotExist() {
        // Arrange
        Integer cartId = 1;
        Optional<Cart> expectedCart = Optional.empty();
        Mockito.when(cartRepository.findById(cartId)).thenReturn(expectedCart);

        // Act
        cartService.getValueOfCart(cartId);
    }

    @Test
    public void testFindCartByIdReturnsCartWhenExists() {
        // Arrange
        Integer cartId = 1;
        double value = 10.0;
        Cart expectedCart = new Cart();
        expectedCart.setCartId(cartId);
        expectedCart.setValue(value);
        Optional<Cart> expectedOptional = Optional.of(expectedCart);
        Mockito.when(cartRepository.findById(cartId)).thenReturn(expectedOptional);

        // Act
        Optional<Cart> actualOptional = cartService.findCartById(cartId);

        // Assert
        assertTrue(actualOptional.isPresent());
        assertEquals(expectedOptional.get(), actualOptional.get());
    }

    @Test
    public void testFindCartByIdReturnsEmptyOptionalWhenNotExists() {
        // Arrange
        Integer cartId = 1;
        Optional<Cart> expectedOptional = Optional.empty();
        Mockito.when(cartRepository.findById(cartId)).thenReturn(expectedOptional);

        // Act
        Optional<Cart> actualOptional = cartService.findCartById(cartId);

        // Assert
        assertFalse(actualOptional.isPresent());
    }

    @Test
    public void testGetCartFromDbReturnsFirstCart() {
        // Arrange
        Cart expectedCart = new Cart();
        Mockito.when(cartRepository.findAll()).thenReturn(Arrays.asList(expectedCart, new Cart(), new Cart()));

        // Act
        Cart actualCart = cartService.getCartFromDb();

        // Assert
        assertEquals(expectedCart, actualCart);
    }
}
