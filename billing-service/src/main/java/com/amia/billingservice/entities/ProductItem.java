package com.amia.billingservice.entities;

import com.amia.billingservice.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItem {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;
    private int quantity;
    private double price;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill;

    @Transient
    private Product product;
}