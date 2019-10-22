package com.pramati.metaconfigapp.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CustomerDetails {


	@Id
	String email;
	String name;
	String shippingAddress;
	String phoneNumber;
	String paymentInformation;

	boolean isRegisteredForSale;

	public CustomerDetails() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPaymentInformation() {
		return paymentInformation;
	}
	public void setPaymentInformation(String paymentInformation) {
		this.paymentInformation = paymentInformation;
	}
	public boolean isRegisteredForSale() {
		return isRegisteredForSale;
	}
	public void setRegisteredForSale(boolean isRegisteredForSale) {
		this.isRegisteredForSale = isRegisteredForSale;
	}
	

}
