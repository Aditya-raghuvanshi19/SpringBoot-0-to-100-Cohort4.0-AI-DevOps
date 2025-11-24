package com.cohort4.week1.BeansAndDependecyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BeansAndDependecyInjectionApplication implements CommandLineRunner {
//
//	@Autowired
//	PaymentService paymentService1;
//	@Autowired
//	PaymentService paymentService2;

	// ? this is field dependency injection
	//	@Autowired
	//	NotificationService notificationService;


	//? the best way is constructor dependencies injection no need to make field as final, it can work with both but profit is that we can make the field as private and final
//	final NotificationService notificationService;
//
//	public BeansAndDependecyInjectionApplication(NotificationService notificationService) {
//		this.notificationService = notificationService;
//	}

    //? by the use of Quantifier
//	public BeansAndDependecyInjectionApplication(@Qualifier("SMS Notif") NotificationService notificationService) {
//		this.notificationService = notificationService;
//	}

	public static void main(String[] args) {
		SpringApplication.run(BeansAndDependecyInjectionApplication.class, args);

//		PaymentService paymentService = new PaymentService();
//		paymentService.pay();
	}

	//! if we want to use the bean of all the implementation of the NotificationService interface then use Autowired and map
	//? using all bean

	@Autowired
	Map<String,NotificationService> notificationServiceMap = new HashMap<>();



	@Override
	public void run(String... args) throws Exception {

//		System.out.println(paymentService1.hashCode());
//		System.out.println(paymentService2.hashCode());
//
//		  paymentService1.pay();
//		  paymentService2.pay();

		//notificationService.send("hello people");


		for(var notificationService: notificationServiceMap.entrySet()){
			System.out.println(notificationService.getKey());
			notificationService.getValue().send("hello user");
		}


		//? some beans which may or may not created then for that we can use the Optional<> and in this case if bean not present the value of that reference will be null
	}
}
//the command line runner gives us a method run which run when all the application context is ready and the application is about to start then all the command line runner run method runs
//we are using it because the object is not static so that is why we can not use that into the main method