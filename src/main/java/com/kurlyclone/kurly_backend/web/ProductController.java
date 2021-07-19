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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    // 상품 상세 페이지 (단일 상품 정보 가져오기)
    @GetMapping(value = "api/v1/product/{id}")
    public ResponseDTO getById(@PathVariable long id) {
        return productService.findOne(id);
    }

    // 이 상품 어때요 (20개)
    @GetMapping(value = "api/v1/offer_deal")
    public ResponseDTO get3FromAllCategories() {
        return productService.getOfferDeal();
    }

    // 특가,혜택 (3개)
    @GetMapping(value = "api/v1/special_deal")
    public ResponseDTO getByDc() {
        return productService.specialDeal();
    }

    //놓치면 후회할 가격 (8개)
    @GetMapping("/api/v1/hot_deal")
    public ResponseDTO getByLowPrice()
    {
       return productService.hotDeal();
    }

}
