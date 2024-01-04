package com.example.test.answer;

import com.example.test.article.Article;
import com.example.test.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final ArticleService articleService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value = "content") String content) {
        Article article = this.articleService.getArticle(id);
        this.answerService.create(article, content);
        return String.format("redirect:/article/detail/%s", id);
    }
}
