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

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ResponseDTO getOutterCategory(String outer)
    {
        outer = outer.toUpperCase();

        List<Long> categoryIds = new ArrayList<>();
        for (InnerCategory innerCategoryEnum : InnerCategory.class.getEnumConstants()) {
            if (innerCategoryEnum.name().contains(outer)) categoryIds.add(innerCategoryEnum.getId());
        }

        List<Product> OuterProductList = productRepository.findAllByCategoryIds(categoryIds);

        ResponseDTO responseDTO=new ResponseDTO();
        try
        {
            responseDTO.setResult(OuterProductList);
            responseDTO.setOk(true);
        }
        catch (Exception e)
        {
            responseDTO.setResult(e.getMessage());
            responseDTO.setOk(false);
        }
        return responseDTO;
    }

    public ResponseDTO getInnerCategory(String inner)
    {
        inner =inner.toUpperCase();
        Optional<Category> innerCategory = categoryRepository.findByInnerCategory(InnerCategory.valueOf(inner));
        List<Product> InnerProductList = productRepository.findAllByCategory(innerCategory.get());

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO.setResult(InnerProductList);
            responseDTO.setOk(true);
        }
        catch (Exception e)
        {
            responseDTO.setResult(e.getMessage());
            responseDTO.setOk(false);
        }

        return responseDTO;
    }
}
