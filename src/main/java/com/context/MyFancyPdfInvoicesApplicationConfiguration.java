package com.context;

import com.ApplicationLauncher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.InvoiceService;
import com.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
//@PropertySource("classpath:/someOtherFile.properties")
public class MyFancyPdfInvoicesApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
