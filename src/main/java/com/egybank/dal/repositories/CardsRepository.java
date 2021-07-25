package com.egybank.dal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egybank.dal.entities.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {

	List<Cards> findByCustomerId(long customerId);

}
