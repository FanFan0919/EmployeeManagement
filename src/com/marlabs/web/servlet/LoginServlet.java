package com.marlabs.web.servlet;

import com.marlabs.domain.Administrator;
//import org.springframework.beans.BeanUtils;
import com.marlabs.service.AdministratorService;
import com.marlabs.service.impl.AdministratorServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String verifycode = request.getParameter("verifycode");
        HttpSession session = request.getSession();
        // check verification code
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(verifycode)) {
            request.setAttribute("login_msg", "Wrong Verification Code");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        // check username and password
        Map<String, String[]> map = request.getParameterMap();
        Administrator administrator = new Administrator();
        try {
            BeanUtils.populate(administrator, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdministratorService service = new AdministratorServiceImpl();
        Administrator loginAdministrator = service.login(administrator);
        if (loginAdministrator != null) {
            session.setAttribute("administrator", loginAdministrator);
            session.setMaxInactiveInterval(600);    // 10 minutes
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } else {
            request.setAttribute("login_msg", "Wrong Username Or Password!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
