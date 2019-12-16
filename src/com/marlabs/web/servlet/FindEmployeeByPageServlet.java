package com.marlabs.web.servlet;

import com.marlabs.domain.Employee;
import com.marlabs.domain.PageBean;
import com.marlabs.service.EmployeeService;
import com.marlabs.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/findEmployeeByPageServlet")
public class FindEmployeeByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // if you haven't login, redirect to login page
        HttpSession session = request.getSession();
        if (session.getAttribute("administrator") == null) {
            request.getRequestDispatcher("./login.jsp").forward(request, response);
        }
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "10";
        }

        EmployeeService service = new EmployeeServiceImpl();
        PageBean<Employee> pb = service.findEmployeeByPage(currentPage, rows);

        request.setAttribute("pb", pb);
        request.getRequestDispatcher("./list.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
