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
	private Users consumer;
	@OneToOne
	private Cart cart;
	@OneToOne
	private Coupon couponUsed;
	
	public Transaction() {}
	public Transaction(Users consumer, Cart cart, Coupon couponUsed) {
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
	public Users getConsumer() {
		return consumer;
	}
	public void setConsumer(Users consumer) {
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
		return "Transaction [transactionId=" + transactionId + ", consumer=" + consumer + ", cart=" + cart
				+ ", couponUsed=" + couponUsed + "]";
	}
	
	
	
	

}
