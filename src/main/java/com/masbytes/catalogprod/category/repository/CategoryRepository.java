package com.masbytes.catalogprod.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masbytes.catalogprod.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by name or other attributes
    // Optional<Category> findByName(String name);

}
