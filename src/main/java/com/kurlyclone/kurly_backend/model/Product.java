package com.kurlyclone.kurly_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_id")
    @JsonIgnore
    private Banner banner;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subTitle;

    @Column(nullable = false)
    private Integer price;

    private Integer dc;

    private Integer original_price;

    public Product(String img, String title, String subTitle, Integer price, Integer dc, Integer original_price) {
        this.img = img;
        this.title = title;
        this.subTitle = subTitle;
        this.price = price;
        this.dc = dc;
        this.original_price = original_price;
    }

    public Product(String img, String title, String subTitle, Integer price) {
        this.img = img;
        this.title = title;
        this.subTitle = subTitle;
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
