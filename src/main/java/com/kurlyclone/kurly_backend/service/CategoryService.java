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

    public List<ResponseDTO> getOutterCategory(String outer) {
        outer = outer.toUpperCase();

        List<Long> categoryIds = new ArrayList<>();
        for (InnerCategory innerCategoryEnum : InnerCategory.class.getEnumConstants()) {
            if (innerCategoryEnum.name().contains(outer)) categoryIds.add(innerCategoryEnum.getId());
        }

        return productRepository.findAllByCategoryIds(categoryIds).stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());

    }

    public List<ResponseDTO> getInnerCategory(String inner) {
        inner = inner.toUpperCase();
        Category innerCategory = categoryRepository.findByInnerCategory(InnerCategory.valueOf(inner))
                .orElseThrow(()->new IllegalArgumentException("해당 아이디는 존재하지 않습니다."));

        return productRepository.findAllByCategory(innerCategory).stream()
                .map(ResponseDTO::new)
                .collect(Collectors.toList());

    }
}
