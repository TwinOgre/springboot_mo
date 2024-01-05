package com.example.test.answer;

import com.example.test.article.Article;
import com.example.test.article.ArticleService;
import com.example.test.user.SiteUser;
import com.example.test.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.Binding;
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
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Article article = this.articleService.getArticle(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("article", article);
            return "article_detail";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        Answer answer =this.answerService.create(article, answerForm.getContent(), siteUser);
        return String.format("redirect:/article/detail/%s#answer_%s", id,answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal){
        Answer answer = this.answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable("id") Integer id, Principal principal){
        if(bindingResult.hasErrors()){
            return "answer_form";
        }
        Answer answer = this.answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.modify(answer,answerForm.getContent());
        return String.format("redirect:/article/detail/%s#answer_%s", answer.getArticle().getId(),answer.getId());
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(@PathVariable("id") Integer id, Principal principal){
        Answer answer = this.answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.delete(id);
        return String.format("redirect:/article/detail/%s",answer.getArticle().getId());
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String vote(@PathVariable("id") Integer id, Principal principal){
        Article article = this.articleService.getArticle(id);
        Answer answer = this.answerService.getAnswer(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.answerService.vote(answer,siteUser);
        return String.format("redirect:/article/detail/%s", answer.getArticle().getId());
    }

}
