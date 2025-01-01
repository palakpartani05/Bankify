package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;

@WebServlet("/CreateAccountServlet")
public class AccountCreationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("customerId");
        String accountType = request.getParameter("accountType");
        double initialDeposit = Double.parseDouble(request.getParameter("initialDeposit"));
        String status = "active";  // Default status for a new account
        
        try {
            // Database connection
            Connection con = DBConnection.getConnection();
            
            // Step 1: Validate the initial deposit amount
            if (initialDeposit < 500) {
                request.setAttribute("errorDepositMessage", "Initial deposit must be greater than or equal to ₹500.");
                request.getRequestDispatcher("/create_account.jsp").forward(request, response);
                return;
            }
            // Step 2: Check if the customer exists
            String checkCustomerSql = "SELECT * FROM customer WHERE customer_id = ?";
            PreparedStatement checkPs = con.prepareStatement(checkCustomerSql);
            checkPs.setString(1, customerId);
            ResultSet rs = checkPs.executeQuery();
            
            if (rs.next()) {
                // Step 2: If customer exists, proceed with account creation
                String createAccountSql = "INSERT INTO account (customer_id, account_type, balance, status) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(createAccountSql);
                ps.setString(1, customerId);
                ps.setString(2, accountType);
                ps.setDouble(3, initialDeposit);
                ps.setString(4, status);
                
                int rowsAffected = ps.executeUpdate();
                String updateAccountSql = "UPDATE customer SET status = 'active' WHERE customer_id = ?";
                PreparedStatement updatePs = con.prepareStatement(updateAccountSql);
                updatePs.setString(1, customerId);
                int rowsAffected1 = updatePs.executeUpdate();
                if (rowsAffected > 0) {
                    request.setAttribute("successMessage", "Account deleted successfully.");
                    
                } else {
                    request.setAttribute("errorMessage", "Error deleting account.");
                }
                if (rowsAffected > 0) {
                    request.setAttribute("successMessage", "Account created successfully for Customer ID: " + customerId);
                } else {
                    request.setAttribute("errorMessage", "Error creating account. Please try again.");
                }
            } else {
                // Step 3: If customer does not exist, show error message
                request.setAttribute("errorMessage", "Customer with ID " + customerId + " is not registered.");
            }
            
            // Forward to the Create Account page
            request.getRequestDispatcher("/create_account.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/create_account.jsp").forward(request, response);
        }
    }
}
