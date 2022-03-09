package com;

public class InvoiceService {

    public Invoice create(String userId, Integer amount, String pdfUrl) {
        //TODO real pdf creation and storing it on network server
        return new Invoice(userId, amount, "https://www.africau.edu/images/default/sample.pdf");
    }
}
