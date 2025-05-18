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
    @Column(name = "description", length = 255)
    @NotBlank(message = "DESCRIPTION field is required")
    @Size(min = 5, max = 255, message = "DESCRIPTION field must be between 3 and 255 characters")
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

    // Getters and Setters
    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set, ONLY FOR TESTING PURPOSES
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * return info's instance
     */
    @Override
    public String toString() {
        return "Category [id=" + id + ", status=" + status + ", name=" + name + ", description=" + description + "]";
    }

}
