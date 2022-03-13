package com.service;

import com.model.Invoice;
import com.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

    private final UserService userService;
    List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public List<Invoice> findAll() {
        return invoices;
    }

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    public Invoice create(String userId, Integer amount) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException();
        }

        //TODO real pdf creation and storing it on network server
        Invoice invoice = new Invoice(userId, amount, "https://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }
}
