package com.egybank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.egybank.dal.entities.AccountTransactions;
import com.egybank.dal.entities.Customer;
import com.egybank.dal.repositories.AccountTransactionsRepository;

@RestController
public class BalanceController {

	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;

	@PostMapping("/balance")
	public List<AccountTransactions> getBalanceDetails(@RequestBody Customer customer) {
		return accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(customer.getId());
	}
}
