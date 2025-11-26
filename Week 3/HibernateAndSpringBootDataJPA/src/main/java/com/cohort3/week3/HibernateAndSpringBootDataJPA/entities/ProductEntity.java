package com.cohort3.week3.HibernateAndSpringBootDataJPA.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_table" ,
uniqueConstraints = {
        @UniqueConstraint(name = "sku_uniques" , columnNames = {"sku"}),
        @UniqueConstraint(name = "title_price_unique" , columnNames = {"product_title","price"})

},indexes = {
        @Index(name = "sku_indexes",columnList = "sku")
}
)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 20)
    private String sku;

    @Column(name = "product_title")
    private String title;
    private BigDecimal price;
    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
//hibernate provides us the way to directly add the data into the table by this application without using the SQL work bench manually
//so for this create a file into the resource folder as the data is also a resource and add these below lines into properties:
