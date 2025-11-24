package com.cohort4.week1.BeansAndDependecyInjection;

import org.springframework.stereotype.Component;

//@Component
public class PaymentService {

    public void pay(){
        System.out.println("Paying...");
    }
}

//Bean is something that is injected via Dependency injection frameWork
