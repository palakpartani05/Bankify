package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("customerId");
        String customerName = request.getParameter("customerName");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");

        try {
            // Assuming that you have the connection details in the database
            Connection con = DBConnection.getConnection();
            
            String sql = "UPDATE customer SET name = ?, address_line1 = ?, address_line2 = ?, city = ?, state = ? WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, addressLine1);
            ps.setString(3, addressLine2);
            ps.setString(4, city);
            ps.setString(5, state);
            ps.setString(6, customerId);

            int result = ps.executeUpdate();

            if (result > 0) {
                response.sendRedirect("customerExecutive.jsp");
            } else {
                request.setAttribute("errorMessage", "Failed to update customer.");
                request.getRequestDispatcher("/update_customer.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/update_customer.jsp").forward(request, response);
        }
    }
}
