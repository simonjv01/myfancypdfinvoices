package com.service;

import com.model.Invoice;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {


    List<Invoice> invoices = new CopyOnWriteArrayList<>();


    private final UserService userService;
    private String cdnUrl;

    public InvoiceService(UserService userService, @Value("${cdn.url}")String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetching PDF Template from S3...");
        // TODO download from s3 and save locally
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Deleting download templates...");
        // TODO actual deletion of PDFs
    }

//    @Autowired
//    public InvoiceService(UserService userService) {
//        this.userService = userService;
//    }

    public List<Invoice> findAll() {
        return invoices;
    }



    public Invoice create(String userId, Integer amount) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException();
        }

        //TODO real pdf creation and storing it on network server
        Invoice invoice = new Invoice(userId, amount, cdnUrl + "/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }


}
