package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;


public class AccountMapRepository implements AccountRepository {

	Map<Long, Account> account = new HashMap<>();
	JSONUtil jsonutil = new JSONUtil();
	String success = "Success";
	String failure = "Failure";

	public String getAllAccounts() {
		return jsonutil.getJSONForObject(account.values());

	}

	public String createAccount(String account) {
		Account newAccount;
		newAccount = jsonutil.getObjectForJSON(account, Account.class);
		this.account.put(newAccount.getAccountNumber(), newAccount);
		return success;
	}

	public String deleteAccount(Long id) {
		if (account.containsKey(id)) {
			account.remove(id);
			return success;
		} else {
			return failure;
		}
	}

	public String updateAccount(Long id, String account) {
		if (this.account.containsKey(id)) {
			Account newAccount = null;
			newAccount = jsonutil.getObjectForJSON(account, Account.class);
			this.account.put(newAccount.getAccountNumber(), newAccount);
			return success;

		} else {
			return failure;
		}
	}

	public long compareFirstNames(String firstName) {
		int count = 0;
		
		for (long key : this.account.keySet()) {
			if(this.account.get(key).getFirstName().equals(firstName)) {
				count++;
			}
		}
		return count;

	}
}