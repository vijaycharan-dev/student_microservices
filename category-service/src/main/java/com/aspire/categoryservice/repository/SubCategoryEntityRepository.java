package com.aspire.categoryservice.repository;

import com.aspire.categoryservice.repository.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryEntityRepository extends JpaRepository<SubCategoryEntity, Long> {

    boolean existsBySubCategoryName(String name);
}