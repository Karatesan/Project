package com.fdmgroup.Confidential_secret_project.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Coupon {
	
	@Id
	@GeneratedValue
	private Integer couponId;
	private int counter;
	@OneToOne
	private User owner;
	
	public Coupon() {}
	public Coupon(int counter, User owner) {
		super();
		this.counter = counter;
		this.owner = owner;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	@Override
	public int hashCode() {
		return Objects.hash(counter, couponId, owner);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		return counter == other.counter && Objects.equals(couponId, other.couponId)
				&& Objects.equals(owner, other.owner);
	}
	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", counter=" + counter + ", owner=" + owner + "]";
	}
}
