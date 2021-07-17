package com.kurlyclone.kurly_backend.runner;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.InnerCategory;
import com.kurlyclone.kurly_backend.model.OuterCategory;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

// setup runner 는 처음 setup 시에만 적용되어야 합니다.
// ddl create 를 하지 않는 이상 주석을 해제하지 마세요.
// @Component
@RequiredArgsConstructor
public class SetupRunner implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    private void createAllCategory(){
        categoryRepository.save(new Category(OuterCategory.FRUIT, InnerCategory.FRUIT1));
        categoryRepository.save(new Category(OuterCategory.FRUIT, InnerCategory.FRUIT2));
        categoryRepository.save(new Category(OuterCategory.FRUIT, InnerCategory.FRUIT3));
        categoryRepository.save(new Category(OuterCategory.VEGE, InnerCategory.VEGE1));
        categoryRepository.save(new Category(OuterCategory.VEGE, InnerCategory.VEGE2));
        categoryRepository.save(new Category(OuterCategory.VEGE, InnerCategory.VEGE3));
        categoryRepository.save(new Category(OuterCategory.SEAFOOD, InnerCategory.SEAFOOD1));
        categoryRepository.save(new Category(OuterCategory.SEAFOOD, InnerCategory.SEAFOOD2));
        categoryRepository.save(new Category(OuterCategory.SEAFOOD, InnerCategory.SEAFOOD3));
    }

    @Override
    public void run(String...args) {
        createAllCategory();
    }
}


