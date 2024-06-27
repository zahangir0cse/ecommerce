package com.crackcode.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Sale extends BaseEntity{
    private LocalDate date;
    private Integer quantity;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
