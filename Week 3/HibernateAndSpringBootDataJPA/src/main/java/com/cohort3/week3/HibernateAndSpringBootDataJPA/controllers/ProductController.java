package com.cohort3.week3.HibernateAndSpringBootDataJPA.controllers;

import com.cohort3.week3.HibernateAndSpringBootDataJPA.entities.ProductEntity;
import com.cohort3.week3.HibernateAndSpringBootDataJPA.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private  final int PAGE_SIZE = 5;

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //    @GetMapping
    //    public List<ProductEntity> getAllProducts(){
    //        return productRepository.findByOrderByPrice();
    //    }

    @GetMapping
    public List<ProductEntity> getAllProduct(@RequestParam(defaultValue = "") String title,
                                             @RequestParam(defaultValue = "id") String sortBy,
                                             @RequestParam(defaultValue = "0") Integer pageNumber){ // index of pageNumber start from 0

        // return productRepository.findBy(Sort.by(sortBy)); // default is ASC order
        //? another way
        //return productRepository.findBy(Sort.by(Sort.Order.desc(sortBy)));

        //* more than 1 field
        //return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy , "price")); //? if two item having the same value of sortBy then they will sort according to the price
        // return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy , "price" , "quantity")); //? if two item having the same value of sortBy and price then they will sort according to the quantity
        //? another way
        //return productRepository.findBy(Sort.by(Sort.Order.desc(sortBy) , Sort.Order.asc("price"))); // it will sort in decreasing by sortBy , but if two object having the same sortBy it will sort acending order by price // we can add more property here

        //! Paging

       //Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE);
        //? paging with sorting
//        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(sortBy));

        // return productRepository.findAll(pageable); // from this we get Page<Product Entity> and accordingly we have to change the return type

        //return productRepository.findAll(pageable).getContent();

        //! combining all filter + paging + sorting

        return productRepository.findByTitleContainingIgnoreCase(title,PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(sortBy)));

        //? in actual the paging is like applying the limit clause in SQL
        //* ex: select
        //        pe1_0.id,
        //        pe1_0.created_at,
        //        pe1_0.price,
        //        pe1_0.quantity,
        //        pe1_0.sku,
        //        pe1_0.product_title,
        //        pe1_0.updated_at
        //    from
        //        product_table pe1_0
        //    where
        //        upper(pe1_0.product_title) like upper(?) escape '\\'
        //    order by
        //        pe1_0.quantity
        //    limit  ?

    }


}
