package com.kurlyclone.kurly_backend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String img;

     @OneToMany(mappedBy = "banner", fetch = FetchType.LAZY)
     private List<Product> products = new ArrayList<>();
}
