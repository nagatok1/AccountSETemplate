package com.qa.MapTests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.Utils.JSONUtil;
import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;

public class AccountServiceTest {

	private AccountMapRepository myrepo;
	private JSONUtil jsonutil;

	@Before
	public void setup() {
		jsonutil = new JSONUtil();
		myrepo = new AccountMapRepository();

	}

	@Test
	public void addAccountTest() {
		Account testaccount = new Account("Ben", "Leadbeater", 1L);
		String accountjson = jsonutil.getJSONForObject(testaccount);
		assertEquals("Success", myrepo.createAccount(accountjson));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		long testnumber = accounts[0].getAccountNumber();
		assertEquals("Ben", accounts[0].getFirstName());
		assertEquals("Leadbeater", accounts[0].getLastName());
		assertEquals(1L, testnumber);

	}

	@Test
	public void add2AccountTest() {
		addAccountTest();
		Account testaccount2 = new Account("Java", "Jez", 2L);
		String accountjson2 = jsonutil.getJSONForObject(testaccount2);
		assertEquals("Success", myrepo.createAccount(accountjson2));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals("Java", accounts[1].getFirstName());
		assertEquals("Jez", accounts[1].getLastName());
		long testnumber = accounts[1].getAccountNumber();
		assertEquals(2L, testnumber);
		assertEquals(2, accounts.length);

	}

	@Test
	public void removeAccountTest() {
		addAccountTest();
		assertEquals("Success", myrepo.deleteAccount(1L));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals(0, accounts.length);

	}

	@Test
	public void remove2AccountTest() {
		add2AccountTest();
		assertEquals("Success", myrepo.deleteAccount(1L));
		assertEquals("Success", myrepo.deleteAccount(2L));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals(0, accounts.length);

	}

	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() {
		remove2AccountTest();
		assertEquals("Failure", myrepo.deleteAccount(3L));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals(0, accounts.length);

	}

	@Test
	public void updateAccount() {
		addAccountTest();
		Account newaccount = new Account("Joe", "Bloggs", 1L);
		String accountjson = jsonutil.getJSONForObject(newaccount);
		assertEquals("Success", myrepo.createAccount(accountjson));
		Account[] accounts = jsonutil.getObjectForJSON(myrepo.getAllAccounts(), Account[].class);
		assertEquals("Joe", accounts[0].getFirstName());
		assertEquals("Bloggs", accounts[0].getLastName());
		long testnumber = accounts[0].getAccountNumber();
		assertEquals(1L, testnumber);

	}

	@Test
	public void accountConversionToJSONTestWithEmptyMap() {

	}

	@Test
	public void accountConversionToJSONTestEmptyMapWithConversion() {

	}

	@Test
	public void accountConversionToJSONTest() {
		Account testaccount = new Account("Ben", "Leadbeater", 1L);
		String accountjson = jsonutil.getJSONForObject(testaccount);
		System.out.println(accountjson);
		assertEquals("{\"accountNumber\":1,\"firstName\":\"Ben\",\"lastName\":\"Leadbeater\"}", accountjson);
	}

	@Test
	public void getCountForFirstNamesInAccountWhenZeroOccurances() {
		addAccountTest();
		assertEquals(0, myrepo.compareFirstNames("Jebediah"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenOne() {
		addAccountTest();
		assertEquals(1, myrepo.compareFirstNames("Ben"));
	}

	@Test
	public void getCountForFirstNamesInAccountWhenMult() {
		Account testaccount1 = new Account("Ben", "Leadbeater", 1L);
		Account testaccount2 = new Account("Ben", "Jordan", 2L);
		Account testaccount3 = new Account("Ben", "Gotcha", 3L);
		Account testaccount4 = new Account("Ben", "Leemer", 4L);

		String accountjson1 = jsonutil.getJSONForObject(testaccount1);
		String accountjson2 = jsonutil.getJSONForObject(testaccount2);
		String accountjson3 = jsonutil.getJSONForObject(testaccount3);
		String accountjson4 = jsonutil.getJSONForObject(testaccount4);

		assertEquals("Success", myrepo.createAccount(accountjson1));
		assertEquals("Success", myrepo.createAccount(accountjson2));
		assertEquals("Success", myrepo.createAccount(accountjson3));
		assertEquals("Success", myrepo.createAccount(accountjson4));

		assertEquals(4, myrepo.compareFirstNames("Ben"));
	}

}
