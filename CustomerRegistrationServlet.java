package com.controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;

@WebServlet("/CustomerRegistrationServlet")
public class CustomerRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ssnId = request.getParameter("ssnId");
        String name = request.getParameter("customerName");
        int age = Integer.parseInt(request.getParameter("age"));
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");

        try {
            Connection con = DBConnection.getConnection();
            // Check if the customer already exists by SSN
            String checkCustomerSql = "SELECT * FROM customer WHERE ssn_id = ?";
            PreparedStatement checkPs = con.prepareStatement(checkCustomerSql);
            checkPs.setString(1, ssnId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                // Customer already exists, show error message
                request.setAttribute("errorMessage", "Customer with SSN " + ssnId + " already exists.");
            } else {
                // Insert new customer into the database and fetch the generated customer_id
                String insertCustomerSql = "INSERT INTO customer (ssn_id, name, age, address_line1, address_line2, city, state) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(insertCustomerSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, ssnId);
                ps.setString(2, name);
                ps.setInt(3, age);
                ps.setString(4, addressLine1);
                ps.setString(5, addressLine2);
                ps.setString(6, city);
                ps.setString(7, state);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    // Retrieve the generated customer_id
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int customerId = generatedKeys.getInt(1);
                        request.setAttribute("successMessage", "Customer creation initiated successfully with Customer ID: " + customerId);
                    } else {
                        request.setAttribute("errorMessage", "Customer created but unable to retrieve Customer ID.");
                    }
                } else {
                    request.setAttribute("errorMessage", "Error creating customer. Please try again.");
                }
            }

            // Forward the request back to create_customer.jsp with success/error message
            request.getRequestDispatcher("/create_customer.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/create_customer.jsp").forward(request, response);
        }
    }
}
