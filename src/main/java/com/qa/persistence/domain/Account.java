package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long accountNumber;
	private String firstName;
	private String lastName;

	public Account(String firstName, String lastName) {
		this.firstName =  firstName;
		this.lastName = lastName;
	}
	
	public Account(String firstName, String lastName, Long accountNumber) {
		this.firstName =  firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
	}
	
	public Account() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

}
