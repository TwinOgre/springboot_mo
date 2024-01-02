package com.example.test.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String articleList(Model model){
        List<Article> aritcleList = this.articleService.list();
        model.addAttribute("articleList", aritcleList);
        return "article_list";
    }
    @GetMapping("/create")
    public String articleCreate(){
        return "article_create";
    }
    @PostMapping("/create")
    public String articleCreate(@Valid ArticleForm articleForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "article_create";
        }
        this.articleService.create(articleForm.getSubject(),articleForm.getContent());
        return "redirect:/article/list";
    }
    @GetMapping("/detail/{id}")
    public String articleDetail(Model model, @PathVariable("id") Integer id){
        Article article = this.articleService.getArticle(id);
        model.addAttribute(article);
        return "article_detail";
    }
}
