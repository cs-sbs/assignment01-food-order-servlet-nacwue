package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理菜单查询 (Query Parameter)
 */
public class MenuListServlet extends HttpServlet {
    private List<MenuItem> menuList;

    @Override
    public void init() throws ServletException {
        menuList = new ArrayList<>();
        menuList.add(new MenuItem("Fried Rice", 8));
        menuList.add(new MenuItem("Fried Noodles", 9));
        menuList.add(new MenuItem("Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String searchName = req.getParameter("name");

        out.println("Menu List:\n");
        int index = 1;
        boolean found = false; // 新增：用于记录是否找到了匹配的菜品

        for (MenuItem item : menuList) {
            if (searchName == null || searchName.isEmpty() || item.getName().toLowerCase().contains(searchName.toLowerCase())) {
                out.println(index + ". " + item.getName() + " - $" + item.getPrice());
                index++;
                found = true; // 只要进来了，就说明找到了
            }
        }

        // 新增：如果没有找到任何匹配项，输出测试脚本期望的提示信息（包含 "No"）
        if (!found) {
            out.println("No items found matching your search.");
        }
    }
}