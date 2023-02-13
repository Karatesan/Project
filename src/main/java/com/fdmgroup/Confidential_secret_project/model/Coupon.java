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
	private double theValue;
	private boolean status;
	@OneToOne
	private Users owner;
	
	public Coupon() {}

	public Coupon(int counter, double theValue, Users owner) {
		super();
		this.counter = counter;
		this.theValue = theValue;
		this.status = true;
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

	public double getTheValue() {
		return theValue;
	}

	public void setTheValue(double theValue) {
		this.theValue = theValue;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Users getOwner() {
		return owner;
	}

	public void setOwner(Users owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(counter, couponId, owner, status, theValue);
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
				&& Objects.equals(owner, other.owner) && status == other.status
				&& Double.doubleToLongBits(theValue) == Double.doubleToLongBits(other.theValue);
	}

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", counter=" + counter + ", theValue=" + theValue + ", status=" + status
				+ ", owner=" + owner + "]";
	}
	
	
	
}
