package com.cohort3.week3.HibernateAndSpringBootDataJPA.repositories;

import com.cohort3.week3.HibernateAndSpringBootDataJPA.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findByTitle(String title);
    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);
    List<ProductEntity> findByQuantityAndPrice(int quantity, BigDecimal price);
    List<ProductEntity> findByQuantityGreaterThanAndPriceLessThan(int quantity, BigDecimal price);
    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike (String title);

    List<ProductEntity> findByTitleContains (String title);

    //List<ProductEntity> findByTitleContainingIgnoreCase(String title);
    List<ProductEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);

   Optional<ProductEntity> findByTitleAndPrice(String title,BigDecimal price); //from this only the single entity will found so be confident that the field we are using to fetch must define the entity as a unique.(as here both is targeted as uniqueConstraint

    //! for defining more custom and complex queries we can write our SQL or JPQL query its up to us

    @Query("select e from ProductEntity e where e.title = ?1 and e.price = ?2") //? select e :-> here e means everything if want specific just write select e.title  ,, this is JPQL
    Optional<ProductEntity> findByTitleAndPriceByCustom(String title,BigDecimal price);

    //? another way to pass parameter :->  @Query("select e from ProductEntity e where e.title =: title and e.price =: price")
    //* we are writing here and using the names that are put into the class and properties as this are define at the code level and java level so code which we write we also have to write at code level or java level or class level ,, the column name or table is defined at the SQL level and that part will be handle by Hibernate to convert java level name into sql level name

//!------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //? L- 3.4 Pagination and Sorting

    //* Sorting
    // 1st way : Sorting with method queries

    List<ProductEntity> findByTitleOrderByPrice(String title);
    List<ProductEntity> findByOrderByPrice();

    //? here in this case for the same kind of work where target to another attribute we have to create a new function
    // So we are restricted with this way  , we are tightly coupled with the field ordered by

    //2nd way by using the Sort class

    List<ProductEntity> findBy(Sort sort);

}
