package com.kurlyclone.kurly_backend.service;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.InnerCategory;
import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import com.kurlyclone.kurly_backend.web.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private List<Long> getInnerCategoryIdsFromOuterStr(String outer) {
        List<Long> categoryIds = new ArrayList<>();

        for (InnerCategory innerCategoryEnum : InnerCategory.class.getEnumConstants()) {
            if (innerCategoryEnum.name().contains(outer)) categoryIds.add(innerCategoryEnum.getId());
        }

        return categoryIds;

    }

    public List<ResponseDTO> getOuterCategory(String outer) {
        outer = outer.toUpperCase();

        List<Long> categoryIds = getInnerCategoryIdsFromOuterStr(outer);

        return productRepository.findAllByCategoryIds(categoryIds).stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());

    }

    public List<ResponseDTO> getOuterCategory(String outer, String sort) {
        outer = outer.toUpperCase();
        sort = sort.toUpperCase();

        List<Long> categoryIds = getInnerCategoryIdsFromOuterStr(outer);
        List<Product> products;

        if (sort.equals("DESC")) {
            products = productRepository.findAllByCategoryIdsOrderByPriceDesc(categoryIds);
        } else if (sort.equals("ASC")) {
            products = productRepository.findAllByCategoryIdsOrderByPriceAsc(categoryIds);
        } else {
            throw new IllegalArgumentException("sort parameter 값이 ASC, DESC 가 아닙니다.");
        }

        return products.stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());

    }

    public List<ResponseDTO> getInnerCategory(String inner) {
        inner = inner.toUpperCase();

        Category innerCategory = categoryRepository.findByInnerCategory(InnerCategory.valueOf(inner))
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다."));

        return productRepository.findAllByCategory(innerCategory).stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());

    }

    public List<ResponseDTO> getInnerCategory(String inner, String sort) {
        inner = inner.toUpperCase();
        sort = sort.toUpperCase();

        Category innerCategory = categoryRepository.findByInnerCategory(InnerCategory.valueOf(inner))
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다."));

        List<Product> products;

        if (sort.equals("DESC")) {
            products = productRepository.findAllByCategoryOrderByPriceDesc(innerCategory);
        } else if (sort.equals("ASC")) {
            products = productRepository.findAllByCategoryOrderByPriceAsc(innerCategory);
        } else {
            throw new IllegalArgumentException("sort parameter 값이 ASC, DESC 가 아닙니다.");
        }

        return products.stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());

    }
}
