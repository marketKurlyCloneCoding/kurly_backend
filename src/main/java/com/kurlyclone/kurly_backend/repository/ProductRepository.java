package com.kurlyclone.kurly_backend.repository;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE p.category IN (:categories)")
    List<Product> findAllByCategories(@Param("categories") List<Category> categories);

    @Query("SELECT p FROM Product p WHERE p.category.id IN (:categoryIds)")
    List<Product> findAllByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

    @Query("SELECT p FROM Product p WHERE p.category IN (:categories)")
    List<Product> findAllByCategories(@Param("categories") List<Category> categories, Pageable pageable);

    List<Product> findAllByOrderByDcDesc(Pageable pageable);

    //놓치면 후회할 가격
    @Query("select p from Product p order by p.price asc")
    List<Product> findAllByLowestProduct(Pageable pageable);


}
