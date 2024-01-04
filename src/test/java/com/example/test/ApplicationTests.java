package com.example.test;

import com.example.test.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	UserService userService;

	@Test
	void test2(){
		this.userService.create("test02","test02@test.com","1234");
	}
}
