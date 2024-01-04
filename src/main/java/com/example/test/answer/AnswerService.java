package com.example.test.answer;

import com.example.test.article.Article;
import com.example.test.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Article article, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setArticle(article);
        answer.setAuthor(author);

        this.answerRepository.save(answer);
    }
}
