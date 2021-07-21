package com.kurlyclone.kurly_backend.web;

import com.kurlyclone.kurly_backend.service.CategoryService;
import com.kurlyclone.kurly_backend.web.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
@RequestMapping("api/v1/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    // outer Category
    @GetMapping(value = "", params = "outer")
    public ResponseEntity<List<ResponseDTO>> getByOuterCategory(String outer) {
        return ResponseEntity.ok().body(categoryService.getOuterCategory(outer));
    }

    // outer sort Category
    @GetMapping(value = "", params = {"outer", "sort"})
    public ResponseEntity<List<ResponseDTO>> getSortByOuterCategory(String outer, String sort) {
        return ResponseEntity.ok().body(categoryService.getOuterCategory(outer, sort));
    }

    // inner Category
    @GetMapping(value = "", params = "inner")
    public ResponseEntity<List<ResponseDTO>> getByInnerCategory(String inner) {
        return ResponseEntity.ok().body(categoryService.getInnerCategory(inner));
    }

    // inner sort Category
    @GetMapping(value = "", params = {"inner", "sort"})
    public ResponseEntity<List<ResponseDTO>> getByInnerCategory(String inner, String sort) {
        return ResponseEntity.ok().body(categoryService.getInnerCategory(inner, sort));
    }
}
