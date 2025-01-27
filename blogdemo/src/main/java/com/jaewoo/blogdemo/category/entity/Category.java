package com.jaewoo.blogdemo.category.entity;

import com.jaewoo.blogdemo.article.entity.Article;
import com.jaewoo.blogdemo.common.baseentity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "category")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 55)
    private String name;

    public static Category of(String name) {
        return Category.builder()
                .name(name)
                .build();
    }

    public void changeName(String name) {
        this.name = name;
    }
}
