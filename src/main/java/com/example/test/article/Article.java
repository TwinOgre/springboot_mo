package com.example.test.article;

import com.example.test.answer.Answer;
import com.example.test.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;


    @CreatedDate
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;
}
