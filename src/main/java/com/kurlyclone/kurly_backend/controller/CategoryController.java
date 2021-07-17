package com.kurlyclone.kurly_backend.controller;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.InnerCategory;
import com.kurlyclone.kurly_backend.model.OuterCategory;
import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    // outer
    @GetMapping(value = "api/category", params = "outer")
    public List<Product> getByOuterCategory2(String outer){
        // query : 1개
        // 이 쿼리도 없앨 수 있습니다. enum 내부 관계가 연결된 상태로 정의
        List<Category> outerCategory = categoryRepository.findAllByOuterCategory(OuterCategory.valueOf(outer));
        // query : 1개
        return productRepository.findAllByCategories(outerCategory);
    }

    // inner
    @GetMapping(value = "api/category", params = "inner")
    public List<Product> getByCategory(String inner){
        Optional<Category> innerCategory = categoryRepository.findByInnerCategory(InnerCategory.valueOf(inner));
        if (innerCategory.isPresent()){
            return productRepository.findAllByCategory(innerCategory.get());
        }
        else {
            // 처리
            return null;
        }
    }
}
