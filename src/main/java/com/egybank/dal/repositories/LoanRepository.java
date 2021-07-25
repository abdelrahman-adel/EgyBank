package com.egybank.dal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egybank.dal.entities.Loans;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {

	List<Loans> findByCustomerIdOrderByStartDtDesc(long customerId);

}
