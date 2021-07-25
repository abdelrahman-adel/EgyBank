package com.egybank.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.egybank.dal.entities.Customer;
import com.egybank.dal.repositories.CustomerRepository;
import com.egybank.security.model.SecurityCustomer;

@Service
public class EgyBankUserDetailService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Customer> customers = customerRepository.findByEmail(username);
		if (CollectionUtils.isEmpty(customers)) {
			throw new UsernameNotFoundException("User couldn't be found : " + username);
		}
		return new SecurityCustomer(customers.get(0));
	}
}
