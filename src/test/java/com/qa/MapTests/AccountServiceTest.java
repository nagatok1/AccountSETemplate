package com.qa.MapTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.persistence.repository.JSONUtil;

public class AccountServiceTest {
	
	AccountMapRepository myrepo;
	JSONUtil jsonutil;

	@Before
	public void setup() {
		jsonutil = new JSONUtil();
		myrepo = new AccountMapRepository();
	
	}
	
	@Test
	public void addAccountTest() {
		Account testaccount = new Account("Ben", "Leadbeater", 1L);
		String accountjson = jsonutil.getJSONForObject(testaccount);
		assertEquals("Success" , myrepo.createAccount(accountjson));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals("Ben" , accounts[0].getFirstName());
		assertEquals("Leadbeater" , accounts[0].getLastName());
		long testnumber = accounts[0].getAccountNumber();
		assertEquals(1L , testnumber);
		
	}
	
	@Test
	public void add2AccountTest() {
		addAccountTest();
		Account testaccount2 = new Account("Jez", "Grinch", 2L);
		String accountjson2 = jsonutil.getJSONForObject(testaccount2);
		assertEquals("Success" , myrepo.createAccount(accountjson2));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals("Jez" , accounts[1].getFirstName());
		assertEquals("Grinch" , accounts[1].getLastName());
		long testnumber = accounts[1].getAccountNumber();
		assertEquals(2L , testnumber);
		assertEquals(2 ,  accounts.length);
		
	}

	@Test
	public void removeAccountTest() {
		addAccountTest();
		assertEquals("Success" , myrepo.deleteAccount(1L));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals(0 , accounts.length);
		
		
		
	}
	
	@Test
	public void remove2AccountTest() {
		add2AccountTest();
		assertEquals("Success" , myrepo.deleteAccount(1L));
		assertEquals("Success" , myrepo.deleteAccount(2L));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals(0 , accounts.length);
		
	}
	
	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		remove2AccountTest();
		assertEquals("Failure" , myrepo.deleteAccount(3L));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals(0 , accounts.length);
		
	}
	
	@Test
	public void updateAccount() {
		addAccountTest();
		Account newaccount = new Account("Joe", "Bloggs", 1L);
		String accountjson = jsonutil.getJSONForObject(newaccount);
		assertEquals("Success" , myrepo.createAccount(accountjson));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals("Joe" , accounts[0].getFirstName());
		assertEquals("Bloggs" , accounts[0].getLastName());
		long testnumber = accounts[0].getAccountNumber();
		assertEquals(1L , testnumber);
		
	}
	
	@Test
	public void accountConversionToJSONTestWithEmptyMap() {
	
	}
	
	@Test
	public void accountConversionToJSONTestEmptyMapWithConversion() {
	
	}

	@Test
	public void accountConversionToJSONTest() {
		
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		
	}
	
	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		
	}

	@Test
	public void getCountForFirstNamesInAccountWhenMult() {
		
	}

}
