package com.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.InvoiceService;
import com.service.UserService;

public class Application {

    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
    public static final ObjectMapper objectMapper = new ObjectMapper();

}
