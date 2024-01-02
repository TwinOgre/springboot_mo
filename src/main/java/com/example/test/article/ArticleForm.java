package com.example.test.article;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleForm {
    @NotEmpty
    String subject;

    @NotEmpty
    String content;
}
