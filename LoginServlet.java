package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT role, password FROM userstore WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String role = rs.getString("role");

                if (storedPassword.equals(password)) { // Replace with hash comparison in production
                    // Update last login
                    String updateSql = "UPDATE userstore SET created_at = ? WHERE user_id = ?";
                    PreparedStatement updatePs = con.prepareStatement(updateSql);
                    updatePs.setTimestamp(1, new Timestamp(new Date().getTime()));
                    updatePs.setString(2, userId);
                    updatePs.executeUpdate();

                    // Redirect based on role
                    if (role.equalsIgnoreCase("executive")) {
                        response.sendRedirect("customerExecutive.jsp");
                    } else if (role.equalsIgnoreCase("cashier")) {
                        response.sendRedirect("cashierTeller.jsp");
                    }
                } else {
                    response.getWriter().println("Invalid password. Please try again.");
                }
            } else {
                response.getWriter().println("Invalid User ID. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
