package com.example.test.article;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleForm {
    @NotEmpty(message = "제목은 필수항목입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
