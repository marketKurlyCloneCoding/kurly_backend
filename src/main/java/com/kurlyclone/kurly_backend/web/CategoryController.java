package com.kurlyclone.kurly_backend.web;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.InnerCategory;
import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import com.kurlyclone.kurly_backend.service.CategoryService;
import com.kurlyclone.kurly_backend.web.dto.ResponseDTO;
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
    private final CategoryService categoryService;

    // outer Category
    @GetMapping(value = "api/category", params = "outer")
    public ResponseDTO getByOuterCategory(String outer) {
        return categoryService.getOutterCategory(outer);
    }

    // inner Category
    @GetMapping(value = "api/category", params = "inner")
    public ResponseDTO getByInnerCategory(String inner) {
        return categoryService.getInnerCategory(inner);
    }
}
