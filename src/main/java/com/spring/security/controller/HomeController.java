package com.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/home")
public class HomeController {

	@GetMapping("/normal")
	public ResponseEntity<String > normalUser() {
		System.out.println("normal");
		return ResponseEntity.ok("Yes, I am normal user");
	}
	
	@GetMapping("/admin")
	public ResponseEntity<String > adminUser() {
		System.out.println("admin");

		return ResponseEntity.ok("Yes, I am admin user");
	}
	
	@GetMapping("/public")
	public ResponseEntity<String > publicUser() {
		System.out.println("public");

		return ResponseEntity.ok("Yes, I am public user");
	}
}
