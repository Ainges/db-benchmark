package de.thi.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends PanacheEntity {
    @ManyToOne
    public User user;

    public LocalDateTime createdAt;
    public String status;
}
