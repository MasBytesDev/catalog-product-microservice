package com.masbytes.catalogprod.common;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.masbytes.catalogprod.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public class AuditingEntity {

    /**
     * Created date of the entity
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    /**
     * Last updated date of the entity
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    /**
     * Deleted date of the entity
     */
    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt;

    /**
     * Status of the entity 
     * Possible values: ACTIVE, INACTIVE, DELETED
     * Default value: ACTIVE
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NotNull(message = "STATUS field is required")
    protected Status status = Status.ACTIVE;

    // No-args constructor
    public AuditingEntity() {

    }

    // Constructor with parameters
    public AuditingEntity(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, Status status) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
    }

    // Getters and Setters
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}