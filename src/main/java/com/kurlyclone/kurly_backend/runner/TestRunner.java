package com.kurlyclone.kurly_backend.runner;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.InnerCategory;
import com.kurlyclone.kurly_backend.model.OuterCategory;
import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
// @Component
public class TestRunner implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void run(String...args) {

        // 상품 저장 방법

        // 1. inner category 를 찾는다.
        Optional<Category> innerCategory = categoryRepository.findByInnerCategory(InnerCategory.VEGE1);
        if (innerCategory.isPresent()){
            Category category = innerCategory.get();
            // 2. product 객체를 만든다.
            Product product = new Product("www.image.com.jpg","친환경 대파 250g","향이 진하고 단맛도 강한 싱싱한 대파", 3000);
            // 3. product 에 category 를 지정한다.
            product.setCategory(category);
            // 4. product 를 저장한다.
            productRepository.save(product);
        }
    }
}
