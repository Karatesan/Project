package com.fdmgroup.Confidential_secret_project.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue
	private Integer transactionId;
	@OneToOne
	private Customer consumer;
	@OneToOne
	private Cart cart;
	@OneToOne
	private Coupon couponUsed;
	
	public Transaction() {}
	public Transaction(Customer consumer, Cart cart, Coupon couponUsed) {
		super();
		this.consumer = consumer;
		this.cart = cart;
		this.couponUsed = couponUsed;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Customer getConsumer() {
		return consumer;
	}
	public void setConsumer(Customer consumer) {
		this.consumer = consumer;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Coupon getCouponUsed() {
		return couponUsed;
	}
	public void setCouponUsed(Coupon couponUsed) {
		this.couponUsed = couponUsed;
	}
	
	public double getValueAfterCoupon() {
		return cart.getTheValue() - couponUsed.getTheValue();
	}
	@Override
	public int hashCode() {
		return Objects.hash(cart, consumer, couponUsed, transactionId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(cart, other.cart) && Objects.equals(consumer, other.consumer)
				&& Objects.equals(couponUsed, other.couponUsed) && Objects.equals(transactionId, other.transactionId);
	}
	@Override
	public String toString() {
		return transactionId +  ". cart value: " + cart.getTheValue() + ", price after coupon: " + getValueAfterCoupon() + ", coupon id: " +couponUsed.getCouponId();
	}
	
	
	
	

}
