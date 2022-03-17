package com.context;

import com.ApplicationLauncher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.InvoiceService;
import com.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
public class MyFancyPdfInvoicesApplicationConfiguration {

//    @Bean
//    public UserService userService() {
//        return new UserService();
//    }
//
//    @Bean
//    public InvoiceService invoiceService(UserService userService) {
//        return new InvoiceService(userService);
//    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
