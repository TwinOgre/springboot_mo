package com.example.test.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ArticleRepsoitory extends JpaRepository<Article, Integer> {
    Page<Article> findAll(Pageable pageable);
}
