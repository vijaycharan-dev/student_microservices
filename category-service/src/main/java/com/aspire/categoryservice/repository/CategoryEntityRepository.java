package com.aspire.categoryservice.repository;

import com.aspire.categoryservice.repository.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryEntityRepository extends JpaRepository<CategoryEntity,Long > {

    boolean existsByCategoryName(String categoryName);
}