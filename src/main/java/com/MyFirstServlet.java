package com;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.IllegalFormatCodePointException;

public class MyFirstServlet extends HttpServlet {

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
        response.getWriter().print("[]");
    }

}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/invoices")) {

            String userId = request.getParameter("user_id");
            Integer amount = Integer.valueOf(request.getParameter("amount"));
            String urlPdf = request.getParameter("\"https://www.africau.edu/images/default/sample.pdf\"")

            Invoice invoice = new InvoiceService().create(userId, amount, urlPdf);

            response.setContentType("application/json; charset=UTF-8");
            String json = new ObjectMapper().writeValueAsString(invoice);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

