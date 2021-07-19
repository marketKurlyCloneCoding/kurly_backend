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

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //상세 조회
    public ResponseDTO findOne(Long id)
    {
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

    //이 상품 어때요
    public ResponseDTO getOfferDeal()
    {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            // TODO : enum 의 내부 관계가 정의되어 있다면 조금 더 빠르게 만들 수 있습니다.
            List<Category> fruitCategory = categoryRepository.findAllByOuterCategory(OuterCategory.FRUIT);
            List<Category> vegeCategory = categoryRepository.findAllByOuterCategory(OuterCategory.VEGE);
            List<Category> seaFoodCategory = categoryRepository.findAllByOuterCategory(OuterCategory.SEAFOOD);

            List<Product> products = new ArrayList<>();
            products.addAll(productRepository.findAllByCategories(fruitCategory, PageRequest.of(0,6)));
            products.addAll(productRepository.findAllByCategories(vegeCategory, PageRequest.of(0,6)));
            products.addAll(productRepository.findAllByCategories(seaFoodCategory, PageRequest.of(0,8)));

            responseDTO.setResult(products);
            responseDTO.setOk(true);
        } catch (Exception e){
            e.printStackTrace();
            responseDTO.setResult(e.getMessage());
            responseDTO.setOk(false);
        }
        return responseDTO;
    }

    //특가,혜택
    public ResponseDTO specialDeal()
    {
        ResponseDTO responseDTO =new ResponseDTO();
        try {
            List<Product> productList = productRepository.findAllByOrderByDcDesc(PageRequest.of(0, 3));
            responseDTO.setResult(productList);
            responseDTO.setOk(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            responseDTO.setResult((e.getMessage()));
            responseDTO.setOk(false);
        }

        return responseDTO;
    }

    //놓치면 후회 할 가격
    public ResponseDTO hotDeal()
    {
        ResponseDTO responseDTO=new ResponseDTO();
        try{
            List<Product> hotDealProduct = productRepository.findAllByLowestProduct(PageRequest.of(0, 8));
            responseDTO.setResult(hotDealProduct);
            responseDTO.setOk(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            responseDTO.setResult(e.getMessage());
            responseDTO.setOk(false);
        }
        return responseDTO;
    }

}
