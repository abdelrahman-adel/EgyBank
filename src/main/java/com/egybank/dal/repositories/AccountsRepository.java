package com.egybank.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egybank.dal.entities.Account;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

	Account findByCustomerId(long customerId);

}
