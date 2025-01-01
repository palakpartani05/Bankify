package com.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("customerId");

        try {
            // Database connection
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM customer WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerId);
            
            int result = ps.executeUpdate();

            if (result > 0) {
                request.setAttribute("successMessage", "Customer deleted successfully.");
                request.getRequestDispatcher("/delete_customer.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to delete customer.");
                request.getRequestDispatcher("/delete_customer.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/delete_customer.jsp").forward(request, response);
        }
    }
}
