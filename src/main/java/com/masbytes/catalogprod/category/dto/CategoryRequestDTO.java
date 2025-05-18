package com.masbytes.catalogprod.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {

    /**
     * name of the category
     */
    @NotBlank(message = "NAME field is required")
    @Size(min = 3, max = 50, message = "NAME field must be between 3 and 50 characters")
    private String name;

    /**
     * Description of the category
     */
    @NotBlank(message = "DESCRIPTION field is required")
    @Size(min = 5, max = 255, message = "DESCRIPTION field must be between 3 and 255 characters")
    private String description;

    // No-args constructor
    public CategoryRequestDTO() {

    }

    // Constructor with parameters
    public CategoryRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters

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
     * toString method for debugging purposes
     */
    @Override
    public String toString() {
        return "CategoryRequestDTO [name=" + name + ", description=" + description + "]";
    }

}
