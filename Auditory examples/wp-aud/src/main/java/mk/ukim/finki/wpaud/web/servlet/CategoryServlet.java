package mk.ukim.finki.wpaud.web.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.service.CategoryService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h3>User Info</h3>");
        writer.println("IP Address:" + ipAddress + "</br>");
        writer.println("Client Agent:" + clientAgent);
        writer.println("<h1>Category list</h1>");
        writer.println("<ul>");
        this.categoryService.listCategories().stream().forEach(r -> writer.format("<li>%s (%s)</li>", r.getName(), r.getDescription()));
        writer.println("</ul>");

        writer.println("<h3>Enter data:</h3>");
        writer.println("<form method='POST' action='/servlet/category'>");
        writer.println("<label for='name'>Name:</label>");
        writer.println("<input id='name' type='text' name='name'/>");
        writer.println("<label for='desc'>Description:</label>");
        writer.println("<input id='desc' type='text' name='desc'/>");
        writer.println("<input type='submit' value='Submit'/>");
        writer.println("</form>");

        writer.println("</body>");
        writer.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDesc = req.getParameter("desc");
        categoryService.create(categoryName,categoryDesc);
        resp.sendRedirect("/servlet/category");
    }


}
