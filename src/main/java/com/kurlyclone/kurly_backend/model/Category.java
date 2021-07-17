package com.kurlyclone.kurly_backend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OuterCategory outerCategory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InnerCategory innerCategory;

    public Category(OuterCategory outerCategory, InnerCategory innerCategory) {
        this.outerCategory = outerCategory;
        this.innerCategory = innerCategory;
    }
}
