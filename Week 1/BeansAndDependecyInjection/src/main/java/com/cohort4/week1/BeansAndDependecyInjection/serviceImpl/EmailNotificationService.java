package com.cohort4.week1.BeansAndDependecyInjection.serviceImpl;

import com.cohort4.week1.BeansAndDependecyInjection.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component
//! the another way is use Quantifier
@Qualifier("Email Notif")
//@ConditionalOnProperty(name = "notification.type" , havingValue = "email")
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Email sending...."+message);

    }
}
