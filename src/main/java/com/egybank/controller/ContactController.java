package com.egybank.controller;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egybank.dal.entities.Contact;
import com.egybank.dal.repositories.ContactRepository;

@RestController
@RequestMapping("/public")
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;

	@PostMapping("/contact")
	public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
		contact.setContactId(getServiceReqNumber());
		contact.setCreateDt(new Date(System.currentTimeMillis()));
		return contactRepository.save(contact);
	}

	private String getServiceReqNumber() {
		return "SR" + UUID.randomUUID().toString();
	}
}
