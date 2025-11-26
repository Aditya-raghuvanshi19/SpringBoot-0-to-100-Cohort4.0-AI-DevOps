package com.cohort3.week3.HibernateAndSpringBootDataJPA.repositories;

import com.cohort3.week3.HibernateAndSpringBootDataJPA.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findByTitle(String title);
    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);
    List<ProductEntity> findByQuantityAndPrice(int quantity, BigDecimal price);
    List<ProductEntity> findByQuantityGreaterThanAndPriceLessThan(int quantity, BigDecimal price);
    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);


}
