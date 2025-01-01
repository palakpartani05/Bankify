package com.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;
import com.dto.Customer;

/**
 * Servlet implementation class FetchCustomerForDeleteServlet
 */
@WebServlet("/FetchCustomerForDeleteServlet")
public class FetchCustomerForDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("customerId");

        try {
            // Database connection
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM customer WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Populate customer details in the request object
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setAddressLine1(rs.getString("address_line1"));
                customer.setAddressLine2(rs.getString("address_line2"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));

//                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/delete_customer.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Customer not found.");
                request.getRequestDispatcher("/delete_customer.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/delete_customer.jsp").forward(request, response);
        }
    }
}

