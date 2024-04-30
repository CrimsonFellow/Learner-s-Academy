package com.simplilearn.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve username and password from request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (isValidUser(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedIn", true);
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private boolean isValidUser(String username, String password) {
        // For demonstration purposes, let's assume there's a hardcoded admin username and password
        return username.equals("admin") && password.equals("password");
    }
}

