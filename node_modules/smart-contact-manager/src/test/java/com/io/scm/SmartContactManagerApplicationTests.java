package com.io.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.io.scm.service.EmailService;

@SpringBootTest
class SmartContactManagerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService service;

	@Test
	void sendEmailTest() {

		service.sendEmail("vedantmahakal@gmail.com", "Just testing email service",
				"Email from SCM Project working on email service");

	}

}
