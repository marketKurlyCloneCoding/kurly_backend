package com.kurlyclone.kurly_backend.web;

import com.kurlyclone.kurly_backend.service.ProductService;
import com.kurlyclone.kurly_backend.web.dto.ResponseDTO;
import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.OuterCategory;
import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true")
@RequestMapping("api/v1/")
@RestController
public class ProductController {
    private final ProductService productService;

    // 상품 상세 페이지 (단일 상품 정보 가져오기)
    @GetMapping(value = "product/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(productService.findOne(id));
    }

    // 이 상품 어때요 (20개)
    @GetMapping(value = "offer_deal")
    public ResponseEntity<List<ResponseDTO>> get3FromAllCategories() {
        return ResponseEntity.ok().body(productService.getOfferDeal());
    }

    // 특가,혜택 (3개)
    @GetMapping(value = "special_deal")
    public ResponseEntity<List<ResponseDTO>> getByDc() {
        return ResponseEntity.ok().body(productService.specialDeal());
    }

    //놓치면 후회할 가격 (8개)
    @GetMapping("hot_deal")
    public ResponseEntity<List<ResponseDTO>> getByLowPrice()
    {
        return ResponseEntity.ok().body(productService.hotDeal());

    }

}
