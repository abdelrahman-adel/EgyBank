package com.egybank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.egybank.dal.entities.Customer;
import com.egybank.dal.entities.Loans;
import com.egybank.dal.repositories.LoanRepository;

@RestController
public class LoansController {

	@Autowired
	private LoanRepository loanRepository;

	@PostMapping("/loans")
	public List<Loans> getLoanDetails(@RequestBody Customer customer) {
		return loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
	}

}
