package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 处理订单创建 (Form Parameter)
 */
public class OrderCreateServlet extends HttpServlet {
    // 模拟数据库存储订单（使用 public static 方便 DetailServlet 读取）
    public static final List<Order> orderDatabase = new ArrayList<>();
    // 用于生成自增的订单 ID，从 1001 开始
    private static final AtomicInteger idGenerator = new AtomicInteger(1001);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // 获取 Form Parameters
        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String quantityStr = req.getParameter("quantity");

        // 异常处理 1：表单参数为空
        if (customer == null || customer.trim().isEmpty() ||
                food == null || food.trim().isEmpty() ||
                quantityStr == null || quantityStr.trim().isEmpty()) {
            out.println("Error: missing parameters");
            return;
        }

        try {
            // 异常处理 2：数量不是合法数字
            int quantity = Integer.parseInt(quantityStr.trim());
            if (quantity <= 0) {
                out.println("Error: quantity must be greater than 0");
                return;
            }

            // 创建订单并存入模拟数据库
            String orderId = String.valueOf(idGenerator.getAndIncrement());
            Order newOrder = new Order(orderId, customer, food, quantity);
            orderDatabase.add(newOrder);

            // 返回成功信息
            out.println("Order Created: " + orderId);

        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
        }
    }
}