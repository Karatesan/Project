package com.fdmgroup.Confidential_secret_project.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {	
	
	@Id
	@GeneratedValue
	private Integer cartId;
	private double theValue;
	private String name;
	
	public Cart() {}
	
	public Cart(double value, String name) {
		super();
		this.theValue = value;
		this.name = name;
	}
	public double getValue() {
		return theValue;
	}
	public void setValue(double value) {
		this.theValue = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public double getTheValue() {
		return theValue;
	}

	public void setTheValue(double theValue) {
		this.theValue = theValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, theValue);
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
		return Objects.equals(name, other.name)
				&& Double.doubleToLongBits(theValue) == Double.doubleToLongBits(other.theValue);
	}

	@Override
	public String toString() {
		return "Cart [value=" + theValue + ", name=" + name + "]";
	}
	

}
