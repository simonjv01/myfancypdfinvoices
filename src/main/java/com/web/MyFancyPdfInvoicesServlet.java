package com.web;

import com.context.MyFancyPdfInvoicesApplicationConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Invoice;
import com.service.InvoiceService;
import com.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MyFancyPdfInvoicesServlet extends HttpServlet {

    private UserService userService;
    private ObjectMapper objectMapper;
    private InvoiceService invoiceService;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(MyFancyPdfInvoicesApplicationConfiguration.class);

        ctx.registerShutdownHook();
        
        this.userService = ctx.getBean(UserService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
        this.invoiceService = ctx.getBean(InvoiceService.class);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/invoices")) {

            String userId = request.getParameter("user_id");
            Integer amount = Integer.valueOf(request.getParameter("amount"));


            Invoice invoice = invoiceService.create(userId, amount);

            response.setContentType("application/json; charset=UTF-8");
            String json = objectMapper.writeValueAsString(invoice);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (request.getRequestURI().equalsIgnoreCase("/")) {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print(
                """
                        <html>
                        <body>
                        <h1>Hello World</h1>
                        <p>This is my very first, embedded Tomcat, HTML Page!</p>
                        <body>
                        </html>""");

    } else if (request.getRequestURI().equalsIgnoreCase("/invoices")) {
        response.setContentType("application/json; charset=UTF-8");
        List<Invoice> invoices = invoiceService.findAll();
        response.getWriter().print(objectMapper.writeValueAsString(invoices));
    }

}


}

