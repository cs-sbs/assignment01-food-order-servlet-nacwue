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
    // 模拟数据库中的菜单数据
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

        // 获取 Query Parameter: ?name=xxx
        String searchName = req.getParameter("name");

        out.println("Menu List:\n");
        int index = 1;
        for (MenuItem item : menuList) {
            // 如果没有搜索参数，或者菜名包含搜索关键词，则返回该菜品
            if (searchName == null || searchName.isEmpty() || item.getName().toLowerCase().contains(searchName.toLowerCase())) {
                out.println(index + ". " + item.getName() + " - $" + item.getPrice());
                index++;
            }
        }
    }
}