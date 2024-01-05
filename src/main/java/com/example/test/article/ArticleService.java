package com.example.test.article;

import com.example.test.DataNotFoundException;
import com.example.test.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepsoitory articleRepsoitory;

    public void create(String subject, String content, SiteUser user) {
        Article article = new Article();
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setAuthor(user);

        this.articleRepsoitory.save(article);
    }

    public List<Article> list() {
        return this.articleRepsoitory.findAll();
    }

    public Article getArticle(Integer id) {
        Optional<Article> article = this.articleRepsoitory.findById(id);
        if (article.isPresent()) {
            return article.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    public Page<Article> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.articleRepsoitory.findAll(pageable);
    }

    public void modify(Article article, String subject, String content){
        article.setSubject(subject);
        article.setContent(content);
        article.setModifyDate(LocalDateTime.now());

        this.articleRepsoitory.save(article);
    }

    public void vote(Article article, SiteUser siteUser) {
        article.getVoter().add(siteUser);
        this.articleRepsoitory.save(article);
    }
}
