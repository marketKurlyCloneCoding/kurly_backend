package com.kurlyclone.kurly_backend.repository;

import com.kurlyclone.kurly_backend.model.Category;
import com.kurlyclone.kurly_backend.model.InnerCategory;
import com.kurlyclone.kurly_backend.model.OuterCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByOuterCategoryAndInnerCategory(OuterCategory outerCategory, InnerCategory innerCategory);
    List<Category> findAllByOuterCategory(OuterCategory outerCategory);
    Optional<Category> findByInnerCategory(InnerCategory innerCategory);
}
