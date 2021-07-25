package com.egybank.dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egybank.dal.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
