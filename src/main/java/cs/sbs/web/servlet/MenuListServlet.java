package cs.sbs.web.servlet;

import cs.sbs.web.model.DataStore;
import cs.sbs.web.model.MenuItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MenuListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String nameQuery = request.getParameter("name");
//
        out.println("Menu List:");
        int index = 1;
        boolean found = false;

        for (MenuItem item : DataStore.menu) {
            if (nameQuery == null || nameQuery.trim().isEmpty() ||
                    item.getName().toLowerCase().contains(nameQuery.trim().toLowerCase())) {
                out.println(index + ". " + item.getName() + " - $" + item.getPrice());
                index++;
                found = true;
            }
        }

        if (!found) {
            out.println("No items found.");
        }
    }
}