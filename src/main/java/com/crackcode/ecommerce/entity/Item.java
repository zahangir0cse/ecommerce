package com.crackcode.ecommerce.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Item extends BaseEntity {
    private String name;
}
