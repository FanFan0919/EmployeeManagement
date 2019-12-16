package com.marlabs.web.servlet;

import com.marlabs.domain.Employee;
import com.marlabs.service.EmployeeService;
import com.marlabs.service.impl.EmployeeServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/addEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Employee employee = new Employee();
        try {
            BeanUtils.populate(employee, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EmployeeService service = new EmployeeServiceImpl();
        service.addEmployee(employee);

        response.sendRedirect(request.getContextPath()+"/findEmployeeByPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
