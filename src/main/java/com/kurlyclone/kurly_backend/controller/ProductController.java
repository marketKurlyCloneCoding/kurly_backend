package com.kurlyclone.kurly_backend.controller;

import com.kurlyclone.kurly_backend.dto.ResponseDTO;
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
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // 단일상품
    @GetMapping(value = "api/v1/product/{id}")
    public ResponseDTO getById(@PathVariable long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            Product product = productRepository.findById(id)
                    .orElseThrow(()-> new NoSuchElementException("해당 id의 결과가 존재하지 않습니다."));
            responseDTO.setResult(product);
            responseDTO.setOk(true);
        } catch (Exception e){
            e.printStackTrace();
            responseDTO.setResult(e.getMessage());
            responseDTO.setOk(false);
        }
        return responseDTO;
    }

    // 이 상품 어때요
    @GetMapping(value = "api/v1/offer_deal")
    public ResponseDTO get3FromAllCategories() {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            // TODO : enum 의 내부 관계가 정의되어 있다면 조금 더 빠르게 만들 수 있습니다.
            List<Category> fruitCategory = categoryRepository.findAllByOuterCategory(OuterCategory.FRUIT);
            List<Category> vegeCategory = categoryRepository.findAllByOuterCategory(OuterCategory.VEGE);
            List<Category> seaFoodCategory = categoryRepository.findAllByOuterCategory(OuterCategory.SEAFOOD);

            List<Product> products = new ArrayList<>();
            products.addAll(productRepository.findAllByCategories(fruitCategory, PageRequest.of(0,3)));
            products.addAll(productRepository.findAllByCategories(vegeCategory, PageRequest.of(0,3)));
            products.addAll(productRepository.findAllByCategories(seaFoodCategory, PageRequest.of(0,3)));

            responseDTO.setResult(products);
            responseDTO.setOk(true);
        } catch (Exception e){
            e.printStackTrace();
            responseDTO.setResult(e.getMessage());
            responseDTO.setOk(false);
        }
        return responseDTO;
    }

    // 할인률 (놓지면 후회할 가격)
    @GetMapping(value = "api/special_deal")
    public List<Product> getByDc() {
        return productRepository.findAllByOrderByDcDesc(PageRequest.of(0, 6));
    }
}
