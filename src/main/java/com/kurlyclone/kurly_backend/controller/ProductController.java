package com.kurlyclone.kurly_backend.controller;

import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductRepository productRepository;

    // 할인률 (놓지면 후회할 가격)
    @GetMapping(value = "api/special_deal")
    public List<Product> getByDc() {
        return productRepository.findAllByOrderByDcDesc(PageRequest.of(0, 6));
    }
}
