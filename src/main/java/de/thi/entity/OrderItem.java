package de.thi.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class OrderItem extends PanacheEntity {
    @ManyToOne
    public Order order;

    @ManyToOne
    public Product product;

    public int quantity;
    public BigDecimal priceAtOrder;
}

