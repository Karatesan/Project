package com.fdmgroup.Confidential_secret_project.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cart {	
	
	@Id
	@GeneratedValue
	private Integer cartId;
	private double theValue;
	
	public Cart() {}
	
	public Cart(double value) {
		super();
		this.theValue = value;
	}
	public double getValue() {
		return theValue;
	}
	public void setValue(double value) {
		this.theValue = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(theValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Double.doubleToLongBits(theValue) == Double.doubleToLongBits(other.theValue);
	}

}
