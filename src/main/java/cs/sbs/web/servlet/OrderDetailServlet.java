package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理查看订单详情 (Path Parameter)
 */
public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // 获取 Path Parameter。例如请求 /order/1001，getPathInfo() 会返回 "/1001"
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            out.println("Error: Missing order ID in path");
            return;
        }

        // 去掉前面的斜杠 "/" 提取出真正的 ID
        String targetId = pathInfo.substring(1);
        Order foundOrder = null;

        // 在模拟数据库中查找订单
        for (Order order : OrderCreateServlet.orderDatabase) {
            if (order.getId().equals(targetId)) {
                foundOrder = order;
                break;
            }
        }

        // 异常处理 3：查询不到订单
        if (foundOrder == null) {
            out.println("Error: Order not found");
        } else {
            // 按要求输出详情
            out.println("Order Detail\n");
            out.println("Order ID: " + foundOrder.getId());
            out.println("Customer: " + foundOrder.getCustomer());
            out.println("Food: " + foundOrder.getFood());
            out.println("Quantity: " + foundOrder.getQuantity());
        }
    }
}