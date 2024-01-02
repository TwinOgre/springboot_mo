package com.example.test.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepsoitory extends JpaRepository<Article,Integer> {
}
