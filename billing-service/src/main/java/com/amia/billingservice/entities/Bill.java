package com.amia.billingservice.entities;

import com.amia.billingservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue
    private Long id;
    private Date billDate;
    private long customerId;

    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems;

    @Transient
    private Customer customer;
}
