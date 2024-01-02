package com.example.test.article;

import com.example.test.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepsoitory articleRepsoitory;
    public void create(String subject, String content) {
        Article article = new Article();
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());

        this.articleRepsoitory.save(article);
    }

    public List<Article> list() {
        return this.articleRepsoitory.findAll();
    }
    public Article getArticle(Integer id){
        Optional<Article> article = this.articleRepsoitory.findById(id);
        if (article.isPresent()) {
            return article.get();
        } else {
            throw new DataNotFoundException("question not found");
        }

    }
}
