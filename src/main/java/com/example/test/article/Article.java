package com.example.test.article;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

}
