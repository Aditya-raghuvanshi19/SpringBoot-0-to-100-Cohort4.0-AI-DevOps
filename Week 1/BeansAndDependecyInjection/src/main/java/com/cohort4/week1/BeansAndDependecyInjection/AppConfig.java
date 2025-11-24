package com.cohort4.week1.BeansAndDependecyInjection;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype") //by default it is singleton: means only single bean is created for a class throughout the application
    public PaymentService getPayment(){
        // more logic with more control
        return new PaymentService();
    }//it is telling that how the bean are going to be created , now the responsibility of spring framework to detect this annotation and create the bean of this and store the bean of this class into the ioc container, and where the need is just inject that

    @PostConstruct
    public void afterInitialisation(){
        System.out.println("the bean has bean created and all its dependencies has been injected");
    }
    @PreDestroy
    public void beforeDestroy(){
        System.out.println("the bean has been destroyed by garbage collector of spring framework");
    }


//more scope : request,application,session -> per http request on server a new bean created it is for stateful application , where server storing some kind of data related to each api call so that it can verify
    // but we not store state on server we use caching and db for it
    // if we use this we have to keep in mind that our request should go to the same server every time in this case no use of load balancer which is wrong

}
// under the @Bean we have to declare a constructor method that will going to give us the object of class for which we want to create bean
// the @Bean will take the priority over the @Component or similar to this