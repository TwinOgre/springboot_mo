package com.example.test.article;

import com.example.test.user.SiteUser;
import com.example.test.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    @GetMapping("/list")
    public String articleList(Model model) {
        List<Article> aritcleList = this.articleService.list();
        model.addAttribute("articleList", aritcleList);
        return "article_list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String articleCreate(ArticleForm articleForm) {
        return "article_create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String articleCreate(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "article_create";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.articleService.create(articleForm.getSubject(), articleForm.getContent(),siteUser);
        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String articleDetail(Model model, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute(article);
        return "article_detail";
    }
}
