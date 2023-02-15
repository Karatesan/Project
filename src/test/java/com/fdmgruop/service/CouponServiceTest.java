package com.fdmgruop.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Users;
import com.fdmgroup.Confidential_secret_project.repository.CouponRepository;
import com.fdmgroup.Confidential_secret_project.service.CouponService;
import com.fdmgroup.Confidential_secret_project.service.UserService;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

	 @Mock
	    private CouponRepository couponRepository;

	    @Mock
	    private UserService userService;

	    @InjectMocks
	    private CouponService couponService;

	    @Test
	    public void testSaveCallsSaveMethodOfCouponRepository() {
	        // Arrange
	        Coupon coupon = new Coupon();

	        // Act
	        couponService.save(coupon);

	        // Assert
	        Mockito.verify(couponRepository, Mockito.times(1)).save(coupon);
	    }

	    @Test
	    public void testFindByOwnerIdCallsFindByIdMethodOfUserServiceAndFindByOwnerMethodOfCouponRepository() {
	        // Arrange
	        Integer ownerId = 1;
	        Users owner = new Users();
	        owner.setUserId(ownerId);
	        Coupon coupon1 = new Coupon();
	        Coupon coupon2 = new Coupon();
	        List<Coupon> expectedList = Arrays.asList(coupon1, coupon2);
	        Optional<Users> optionalOwner = Optional.of(owner);
	        Mockito.when(userService.findById(ownerId)).thenReturn(optionalOwner);
	        Mockito.when(couponRepository.findByOwner(owner)).thenReturn(expectedList);

	        // Act
	        List<Coupon> actualList = couponService.findByOwnerId(ownerId);

	        // Assert
	        assertEquals(expectedList, actualList);
	        Mockito.verify(userService, Mockito.times(1)).findById(ownerId);
	        Mockito.verify(couponRepository, Mockito.times(1)).findByOwner(owner);
	    }

	    @Test
	    public void testFindByOwnerIdReturnsNullWhenOwnerDoesNotExist() {
	        // Arrange
	        Integer ownerId = 1;
	        Optional<Users> optionalOwner = Optional.empty();
	        Mockito.when(userService.findById(ownerId)).thenReturn(optionalOwner);

	        // Act
	        List<Coupon> actualList = couponService.findByOwnerId(ownerId);

	        // Assert
	        assertNull(actualList);
	        Mockito.verify(userService, Mockito.times(1)).findById(ownerId);
	        Mockito.verify(couponRepository, Mockito.never()).findByOwner(Mockito.any());
	    }

	    @Test
	    public void testUpdateCouponCallsSaveMethodOfCouponRepository() {
	        // Arrange
	        Coupon coupon = new Coupon();

	        // Act
	        couponService.updateCoupon(coupon);

	        // Assert
	        Mockito.verify(couponRepository, Mockito.times(1)).save(coupon);
	    }

	    @Test
	    public void testFindByIdReturnsOptionalOfCouponWhenCouponExists() {
	        // Arrange
	        Integer couponId = 1;
	        Coupon expectedCoupon = new Coupon();
	        expectedCoupon.setCouponId(couponId);
	        Optional<Coupon> expectedOptional = Optional.of(expectedCoupon);
	        Mockito.when(couponRepository.findById(couponId)).thenReturn(expectedOptional);

	        // Act
	        Optional<Coupon> actualOptional = couponService.findById(couponId);

	        // Assert
	        assertTrue(actualOptional.isPresent());
	        assertEquals(expectedOptional.get(), actualOptional.get());
	    }

	    @Test
	    public void testFindByIdReturnsEmptyOptionalWhenCouponDoesNotExist() {
	        // Arrange
	        Integer couponId = 1;
	        Optional<Coupon> expectedOptional = Optional.empty();
	        Mockito.when(couponRepository.findById(couponId)).thenReturn(expectedOptional);

	        // Act
	        Optional<Coupon> actualOptional = couponService.findById(couponId);

	        // Assert
	        assertFalse(actualOptional.isPresent());
	    }

	    @Test
	    public void testFindAllCouponsReturnsListOfCoupons() {
	        // Arrange
	        List<Coupon> expectedList = Arrays.asList(new Coupon(), new Coupon(), new Coupon());
	        Mockito.when(couponRepository.findAll()).thenReturn(expectedList);

	        // Act
	        List<Coupon> actualList = couponService.findAllCoupons();

	        // Assert
	        assertEquals(expectedList, actualList);
	    }
}
