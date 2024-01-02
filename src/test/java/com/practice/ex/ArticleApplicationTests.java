package com.practice.ex;

import com.practice.ex.article.Article;
import com.practice.ex.article.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ArticleApplicationTests {
	@Autowired
	private ArticleRepository articleRepository;

	@Test
	void contextLoads() {
		Article a1 = new Article();
		a1.setSubject("article1 subject");
		a1.setContent("article1 content");
		a1.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(a1);

		Article a2 = new Article();
		a2.setSubject("제목이다");
		a2.setContent("내용이다");
		a2.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(a2);
	}

}
