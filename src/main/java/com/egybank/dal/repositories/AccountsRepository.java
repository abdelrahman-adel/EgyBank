package com.egybank.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egybank.dal.entities.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

	Accounts findByCustomerId(long customerId);

}
