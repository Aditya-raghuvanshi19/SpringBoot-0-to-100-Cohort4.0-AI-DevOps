package com.cohort3.week3.HibernateAndSpringBootDataJPA;

import com.cohort3.week3.HibernateAndSpringBootDataJPA.entities.ProductEntity;
import com.cohort3.week3.HibernateAndSpringBootDataJPA.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class HibernateAndSpringBootDataJpaApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	//@Test
	void testRepository(){
		ProductEntity productEntity = ProductEntity.builder()
				.sku("SKU-0001")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(123.23))
				.quantity(12).build();

		ProductEntity saved=productRepository.save(productEntity);
		System.out.println(saved);
	}

	@Test
	void getRepository(){
		//List<ProductEntity> entities = productRepository.findAll();
		//System.out.println(entities);

		//List<ProductEntity> entities = productRepository.findByTitle("Mechanical Keyboard");

		//List<ProductEntity> entities = productRepository .findByCreatedAtAfter(LocalDateTime.of(2025,11,20,0,0,0));

		//List<ProductEntity> entities = productRepository.findByQuantityAndPrice(75,BigDecimal.valueOf(799));
		//List<ProductEntity> entities = productRepository.findByQuantityGreaterThanAndPriceLessThan(75,BigDecimal.valueOf(799));
		List<ProductEntity> entities = productRepository.findByQuantityGreaterThanOrPriceLessThan(75,BigDecimal.valueOf(799));

		for(ProductEntity pe : entities){
			System.out.println(pe);
		}

	}

}
