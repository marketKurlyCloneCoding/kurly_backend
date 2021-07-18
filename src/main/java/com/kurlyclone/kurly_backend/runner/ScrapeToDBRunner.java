package com.kurlyclone.kurly_backend.runner;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.InnerCategory;
import com.kurlyclone.kurly_backend.model.Product;
import com.kurlyclone.kurly_backend.repository.CategoryRepository;
import com.kurlyclone.kurly_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// @Component
@RequiredArgsConstructor
public class ScrapeToDBRunner implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public String save(BufferedReader csvReader) throws IOException {
        String row = csvReader.readLine();
        String[] data = row.split(",");
        System.out.println("data = " + Arrays.toString(data));

        Category category = null;
        if (data[0].equals("1")) category = categoryRepository.findByInnerCategory(InnerCategory.VEGE1).get();
        if (data[0].equals("2")) category = categoryRepository.findByInnerCategory(InnerCategory.VEGE2).get();
        if (data[0].equals("3")) category = categoryRepository.findByInnerCategory(InnerCategory.VEGE3).get();
        if (data[0].equals("4")) category = categoryRepository.findByInnerCategory(InnerCategory.FRUIT1).get();
        if (data[0].equals("5")) category = categoryRepository.findByInnerCategory(InnerCategory.FRUIT2).get();
        if (data[0].equals("6")) category = categoryRepository.findByInnerCategory(InnerCategory.FRUIT3).get();
        if (data[0].equals("7")) category = categoryRepository.findByInnerCategory(InnerCategory.SEAFOOD1).get();
        if (data[0].equals("8")) category = categoryRepository.findByInnerCategory(InnerCategory.SEAFOOD2).get();
        if (data[0].equals("9")) category = categoryRepository.findByInnerCategory(InnerCategory.SEAFOOD3).get();

        // 2. product 객체를 만든다.
        Product product = null;
        try{
            if (data.length < 6) {
                if (data[4].contains(".")) {data[4] = data[4].split("\\.")[0];}
                product = new Product(data[1], data[2], data[3], Integer.parseInt(data[4]));
            } else {
                if (data[5].contains(".")) {data[5] = data[5].split("\\.")[0];}
                if (data[6].contains(".")) {data[6] = data[6].split("\\.")[0];}
                product = new Product(data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
            }
            // 3. product 에 category 를 지정한다.
            product.setCategory(category);
            // 4. product 를 저장한다.
            productRepository.save(product);
        }catch (Exception e){
            // , 가 sub_title 들어가 있어 , 관련 오류가 납니다. 오류는 무시합니다.
        }
        return row;
    }

    @Override
    public void run(String... args) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("data.csv"));
        String row = csvReader.readLine();
        while (row != null) {
            row = save(csvReader);
        }
    }
}
