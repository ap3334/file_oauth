package com.example.file.app.hashTag.entity;

import com.example.file.app.article.entity.Article;
import com.example.file.app.base.entity.BaseEntity;
import com.example.file.app.keyword.entity.Keyword;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class HashTag extends BaseEntity {

    @ManyToOne
    private Article article;

    @ManyToOne
    private Keyword keyword;

}
