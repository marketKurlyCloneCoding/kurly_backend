package com.kurlyclone.kurly_backend.controller;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.InnerCategory;
import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    // outer Category
    @GetMapping(value = "api/category", params = "outer")
    public List<Product> getByOuterCategory(String outer) {
        outer = outer.toUpperCase();

        // 내부 정의로 변경 (enum 이름 포함관계)
        List<Long> categoryIds = new ArrayList<>();
        for (InnerCategory innerCategoryEnum : InnerCategory.class.getEnumConstants()) {
            if (innerCategoryEnum.name().contains(outer)) categoryIds.add(innerCategoryEnum.getId());
        }
        // query : 1개
        return productRepository.findAllByCategoryIds(categoryIds);
    }

    // inner Category
    @GetMapping(value = "api/category", params = "inner")
    public List<Product> getByInnerCategory(String inner) {
        Optional<Category> innerCategory = categoryRepository.findByInnerCategory(InnerCategory.valueOf(inner));
        if (innerCategory.isPresent()) {
            return productRepository.findAllByCategory(innerCategory.get());
        } else {
            // 처리
            return null;
        }
    }
}
