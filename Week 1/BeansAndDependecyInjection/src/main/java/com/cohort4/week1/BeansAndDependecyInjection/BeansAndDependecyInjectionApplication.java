package com.cohort4.week1.BeansAndDependecyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeansAndDependecyInjectionApplication implements CommandLineRunner {

	@Autowired
	PaymentService paymentService1;
	@Autowired
	PaymentService paymentService2;

	public static void main(String[] args) {
		SpringApplication.run(BeansAndDependecyInjectionApplication.class, args);

//		PaymentService paymentService = new PaymentService();
//		paymentService.pay();

	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(paymentService1.hashCode());
		System.out.println(paymentService2.hashCode());

		  paymentService1.pay();
		  paymentService2.pay();
	}
}
//the command line runner gives us a method run which run when all the application context is ready and the application is about to start then all the command line runner run method runs
//we are using it because the object is not static so that is why we can not use that into the main method