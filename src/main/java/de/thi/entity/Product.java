package de.thi.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Product extends PanacheEntity {
    public String name;
    public String description;
    public BigDecimal price;
    public String category;
    public int stock;
    public LocalDateTime createdAt;
}
