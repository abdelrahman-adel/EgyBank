package com.egybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

	@GetMapping("/secret")
	public String getSecret(String param) {
		return "secret";
	}

}
