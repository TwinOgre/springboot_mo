package com.example.test.answer;

import com.example.test.article.Article;
import com.example.test.article.ArticleService;
import com.example.test.user.SiteUser;
import com.example.test.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final ArticleService articleService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value = "content") String content, Principal principal) {
        Article article = this.articleService.getArticle(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.answerService.create(article, content, siteUser);
        return String.format("redirect:/article/detail/%s", id);
    }
}
