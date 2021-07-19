package com.kurlyclone.kurly_backend.web;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.InnerCategory;
import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import com.kurlyclone.kurly_backend.service.CategoryService;
import com.kurlyclone.kurly_backend.web.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("api/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    // outer Category
    @GetMapping(value = "", params = "outer")
    public ResponseEntity<List<ResponseDTO>> getByOuterCategory(String outer) {
        return ResponseEntity.ok().body(categoryService.getOutterCategory(outer));
    }

    // inner Category
    @GetMapping(value = "", params = "inner")
    public ResponseEntity<List<ResponseDTO>> getByInnerCategory(String inner) {
        return ResponseEntity.ok().body(categoryService.getInnerCategory(inner));
    }
}
