package com.masbytes.catalogprod.category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masbytes.catalogprod.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String normalizedName);

    Optional <Category> findByName(String normalizedName);
}
