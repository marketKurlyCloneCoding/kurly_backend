package com.kurlyclone.kurly_backend.repository;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.OuterCategory;
import com.kurlyclone.kurly_backend.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findAllByCategory(Category category);

    List<Product> findAllByCategoryOrderByPriceDesc(Category category);

    List<Product> findAllByCategoryOrderByPriceAsc(Category category);


    @Query("SELECT p FROM Product p WHERE p.category.id IN (:categoryIds)")
    List<Product> findAllByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

    @Query("SELECT p FROM Product p WHERE p.category.id IN (:categoryIds) ORDER BY p.price DESC")
    List<Product> findAllByCategoryIdsOrderByPriceDesc(@Param("categoryIds") List<Long> categoryIds);

    @Query("SELECT p FROM Product p WHERE p.category.id IN (:categoryIds) ORDER BY p.price ASC")
    List<Product> findAllByCategoryIdsOrderByPriceAsc(@Param("categoryIds") List<Long> categoryIds);

    @Query("select p from Product p where p.category.outerCategory In (:outerCategory)")
    List<Product> findAllByTest(@Param("outerCategory") OuterCategory outerCategory, Pageable pageable);


    //특가, 혜택
    List<Product> findTop3ByOrderByDcDesc();

    //놓치면 후회할 가격
    List<Product> findTop8ByOrderByPriceAsc();


}
