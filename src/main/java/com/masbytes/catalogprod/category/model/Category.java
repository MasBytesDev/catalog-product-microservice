package com.masbytes.catalogprod.category.model;

import com.masbytes.catalogprod.common.AuditingEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "category", schema = "catalog")
public class Category extends AuditingEntity {

    /**
     * ID of the category
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /**
     * Name of the category
     */
    @Column(name = "name", nullable = false, unique = true, length = 50)
    @NotBlank(message = "NAME field is required")
    @Size(min = 3, max = 50, message = "NAME field must be between 3 and 50 characters")
    private String name;

    /**
     * Description of the category
     */
    @Column(name = "description", nullable = false, length = 255)
    @NotBlank(message = "DESCRIPTION field is required")
    @Size(min = 5, max = 255, message = "DESCRIPTION field must be between 5 and 255 characters")
    private String description;

    // No-args constructor
    public Category() {
    }

    // Constructor with parameters
    public Category(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    // Constructor with ID
    public Category(Long id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    // Only for testing
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Category [id=%s, name=%s, description=%s]",
                id != null ? id.toString() : "null",
                name != null ? name : "null",
                description != null ? description : "null");
    }

}
