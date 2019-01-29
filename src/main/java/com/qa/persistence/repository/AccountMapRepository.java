package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;

import com.qa.persistence.domain.Account;

public class AccountMapRepository implements AccountRepository {

	Map<Long, Account> account = new HashMap<>();
	JSONUtil jsonutil = new JSONUtil();

	public String getAllAccounts() {
		return jsonutil.getJSONForObject(account.values());

	}

	public String createAccount(String account) {
		Account newAccount;
		newAccount = jsonutil.getObjectForJSON(account, Account.class);
		this.account.put(newAccount.getAccountNumber(), newAccount);
		return "Success";
	}

	public String deleteAccount(Long id) {
		if(account.containsKey(id)) {
			account.remove(id);
			return "Success";
		} else {
			return "Failure";
		}
	}

	public String updateAccount(Long id, String account) {
		if (this.account.containsKey(id)) {
			Account newAccount = null;
			newAccount = jsonutil.getObjectForJSON(account, Account.class);
			this.account.put(newAccount.getAccountNumber(), newAccount);
			return "Success";

		} else
			return "Failure";
	}

}
