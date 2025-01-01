package com.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;
import com.dto.Account;

/**
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter("customerId");
        String action = request.getParameter("action"); // to differentiate search from delete action

        try {
            // Database connection
            Connection con = DBConnection.getConnection();
            
            // When action is 'search', retrieve account details
            if ("search".equals(action)) {
                String sql = "SELECT * FROM account WHERE customer_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, customerId);
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    Account account = new Account();
                    account.setAccountId(rs.getString("account_id"));
                    account.setCustomerId(rs.getString("customer_id"));
                    account.setAccountType(rs.getString("account_type"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setStatus(rs.getString("status"));
                    request.setAttribute("account", account);
                } else {
                    request.setAttribute("errorMessage", "Account not found for the given Customer ID.");
                }
            }
            
            // When action is 'delete', delete the account if balance is 0
            if ("delete".equals(action)) {
                String deleteSql = "SELECT * FROM account WHERE customer_id = ?";
                PreparedStatement ps = con.prepareStatement(deleteSql);
                ps.setString(1, customerId);
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    if (balance == 0) {
                        String deleteAccountSql = "DELETE FROM account WHERE customer_id = ?";
                        PreparedStatement deletePs = con.prepareStatement(deleteAccountSql);
                        deletePs.setString(1, customerId);
                        int rowsAffected = deletePs.executeUpdate();
                        
                        String updateAccountSql = "UPDATE customer SET status = 'inactive' WHERE customer_id = ?";
                        PreparedStatement updatePs = con.prepareStatement(updateAccountSql);
                        updatePs.setString(1, customerId);
                        int rowsAffected1 = updatePs.executeUpdate();
                        if (rowsAffected > 0) {
                            request.setAttribute("successMessage", "Account deleted successfully.");
                            
                        } else {
                            request.setAttribute("errorMessage", "Error deleting account.");
                        }
                        
                    } else {
                        request.setAttribute("errorMessage", "Account balance must be 0 to delete the account.");
                    }
                } else {
                    request.setAttribute("errorMessage", "Account not found for the given Customer ID.");
                }
            }
            
            // Forward to the JSP page for displaying results
            request.getRequestDispatcher("/delete_account.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/delete_account.jsp").forward(request, response);
        }
    }
}
