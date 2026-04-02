package cs.sbs.web.servlet;

import cs.sbs.web.model.DataStore;
import cs.sbs.web.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            out.println("Error: Missing order ID in path");
            return;
        }

        try {
            String idStr = pathInfo.substring(1).replaceAll("[^0-9]", "");

            if (idStr.isEmpty()) {
                out.println("Error: Invalid order ID format");
                return;
            }

            int orderId = Integer.parseInt(idStr);

            Order foundOrder = null;
            for (Order order : DataStore.orders) {
                if (order.getId() == orderId) {
                    foundOrder = order;
                    break;
                }
            }

            if (foundOrder != null) {
                out.println("Order Detail");
                out.println("Order ID: " + foundOrder.getId());
                out.println("Customer: " + foundOrder.getCustomer());
                out.println("Food: " + foundOrder.getFood());
                out.println("Quantity: " + foundOrder.getQuantity());
            } else {
                out.println("Error: Order not found");
            }
        } catch (NumberFormatException e) {
            out.println("Error: Invalid order ID format");
        }
    }
}//