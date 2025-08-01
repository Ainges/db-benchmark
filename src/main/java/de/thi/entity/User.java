package de.thi.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
public class User extends PanacheEntity {
    public String name;
    public String email;
    public LocalDateTime createdAt;
}

