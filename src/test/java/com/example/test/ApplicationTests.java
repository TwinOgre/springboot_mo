package com.example.test;

import com.example.test.article.ArticleService;
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
	@Autowired
	ArticleService articleService;
	@Test
	void testJpa() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";

			this.articleService.create(subject, content, null);
		}
	}
}
