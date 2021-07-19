package com.kurlyclone.kurly_backend.service;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.OuterCategory;
import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import com.kurlyclone.kurly_backend.web.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //상세 조회
    public ResponseDTO findOne(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 id의 결과가 존재하지 않습니다."));

        return new ResponseDTO(product);

    }

    //이 상품 어때요
    public List<ResponseDTO> getOfferDeal() {

        List<Category> fruitCategory = categoryRepository.findAllByOuterCategory(OuterCategory.FRUIT);
        List<Category> vegeCategory = categoryRepository.findAllByOuterCategory(OuterCategory.VEGE);
        List<Category> seaFoodCategory = categoryRepository.findAllByOuterCategory(OuterCategory.SEAFOOD);

        List<Product> products = new ArrayList<>();
        products.addAll(productRepository.findAllByCategories(fruitCategory, PageRequest.of(0, 6)));
        products.addAll(productRepository.findAllByCategories(vegeCategory, PageRequest.of(0, 6)));
        products.addAll(productRepository.findAllByCategories(seaFoodCategory, PageRequest.of(0, 8)));

        return products.stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());

    }

    //특가,혜택
    public List<ResponseDTO> specialDeal() {

        List<Product> productList = productRepository.findAllByOrderByDcDesc(PageRequest.of(0, 3));

        return productList.stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());
    }

    //놓치면 후회 할 가격
    public List<ResponseDTO> hotDeal() {

        List<Product> hotDealProduct = productRepository.findAllByLowestProduct(PageRequest.of(0, 8));

        return hotDealProduct.stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());
    }

}
