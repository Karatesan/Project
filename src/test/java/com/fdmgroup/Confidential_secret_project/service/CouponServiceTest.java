package com.fdmgroup.Confidential_secret_project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.fdmgroup.Confidential_secret_project.model.Coupon;
import com.fdmgroup.Confidential_secret_project.model.Customer;
import com.fdmgroup.Confidential_secret_project.repository.CouponRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CouponServiceTest {
	
	@MockBean
	private CouponRepository couponRepo;
	
	@MockBean
	private UserService userService;
	
	@InjectMocks
	private CouponService couponService;
	
	@Test
	public void testSave() {
		Coupon coupon = new Coupon();
		couponService.save(coupon);
		verify(couponRepo).save(coupon);
	}
	
	@Test
	public void testFindByOwnerId() {
		Customer owner = new Customer();
		owner.setUserId(1);
		when(userService.findById(owner.getUserId())).thenReturn(Optional.of(owner));
		
		List<Coupon> coupons = new ArrayList<Coupon>();
		coupons.add(new Coupon());
		when(couponRepo.findByOwner(owner)).thenReturn(coupons);
		
		List<Coupon> result = couponService.findByOwnerId(owner.getUserId());
		
		assertEquals(coupons, result);
		verify(couponRepo, times(1)).findByOwner(owner);
		verify(userService, times(1)).findById(owner.getUserId());
	}
	
	@Test
	public void testUpdateCoupon() {
		Coupon coupon = new Coupon();
		couponService.updateCoupon(coupon);
		verify(couponRepo, times(1)).save(coupon);
	}
	
	@Test
	public void testFindById() {
		Coupon coupon = new Coupon();
		when(couponRepo.findById(1)).thenReturn(Optional.of(coupon));
		
		Optional<Coupon> result = couponService.findById(1);
		
		assertEquals(Optional.of(coupon), result);
		verify(couponRepo, times(1)).findById(1);
	}
	
	@Test
	public void testFindAllCoupons() {
		List<Coupon> coupons = new ArrayList<Coupon>();
		coupons.add(new Coupon());
		when(couponRepo.findAll()).thenReturn(coupons);
		
		List<Coupon> result = couponService.findAllCoupons();
		
		assertEquals(coupons, result);
		verify(couponRepo, times(1)).findAll();
	}
}
	


