package com.cohort4.week1.BeansAndDependecyInjection.serviceImpl;

import com.cohort4.week1.BeansAndDependecyInjection.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Qualifier("SMS Notif")
//! Use the conditional like thing to make the bean of this class
//@ConditionalOnProperty(name = "notification.type" , havingValue = "sms")
public class SendNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("sms sending...."+message);
    }
}
