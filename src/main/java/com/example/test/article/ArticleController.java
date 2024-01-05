package com.example.test.article;

import com.example.test.answer.AnswerForm;
import com.example.test.user.SiteUser;
import com.example.test.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public String articleList(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Article> paging = this.articleService.getList(page);
        model.addAttribute("paging", paging);
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
    public String articleDetail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute(article);
        return "article_detail";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String articleModify(ArticleForm articleForm, @PathVariable("id") Integer id, Principal principal) {
        Article article = this.articleService.getArticle(id);
        if(!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        articleForm.setSubject(article.getSubject());
        articleForm.setContent(article.getContent());
        return "article_create";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String articleModify(@Valid ArticleForm articleForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "article_create";
        }
        Article article = this.articleService.getArticle(id);
        if (!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.articleService.modify(article, articleForm.getSubject(), articleForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete/{id}")
    public String articleDelete(){

        return "redirect:/article/list";
    }

    @GetMapping("/vote/{id}")
    public String vote(@PathVariable("id") Integer id, Principal principal){
        Article article = this.articleService.getArticle(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.articleService.vote(article,siteUser);
        return String.format("redirect:/article/detail/%s", article.getId());
    }
}
