package cs.sbs.web.servlet;

import cs.sbs.web.model.DataStore;
import cs.sbs.web.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderCreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();//

        String customer = request.getParameter("customer");
        String food = request.getParameter("food");
        String quantityStr = request.getParameter("quantity");

        if (customer == null || customer.trim().isEmpty() ||
                food == null || food.trim().isEmpty() ||
                quantityStr == null || quantityStr.trim().isEmpty()) {
            out.println("Error: missing required parameters");
            return;
        }

        try {

            int quantity = Integer.parseInt(quantityStr.trim());
            if (quantity <= 0) {
                out.println("Error: quantity must be greater than 0");
                return;
            }

            int orderId = DataStore.orderIdCounter.getAndIncrement();
            Order order = new Order(orderId, customer, food, quantity);
            DataStore.orders.add(order);

            out.println("Order Created: " + orderId);

        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
        }
    }
}