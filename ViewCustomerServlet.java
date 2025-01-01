package com.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;
import com.dto.Customer;

/**
 * Servlet implementation class ViewCustomerServlet
 */
@WebServlet("/ViewCustomerServlet")
public class ViewCustomerServlet extends HttpServlet {
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
                customer.setStatus(rs.getString("status"));

                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/view_customer.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Customer not found.");
                request.getRequestDispatcher("/view_customer.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/view_customer.jsp").forward(request, response);
        }
    }
}
//
//@WebServlet("/ViewCustomerServlet")
//public class ViewCustomerServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String customerId = request.getParameter("customerId");
//
//        try {
//            // Database connection
//            Connection con = DBConnection.getConnection();
//
//            if (customerId != null && !customerId.trim().isEmpty()) {
//                // Fetch specific customer by ID
//                String sql = "SELECT * FROM customer WHERE customer_id = ?";
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setString(1, customerId);
//                ResultSet rs = ps.executeQuery();
//
//                if (rs.next()) {
//                    Customer customer = new Customer();
//                    customer.setCustomerId(rs.getString("customer_id"));
//                    customer.setName(rs.getString("name"));
//                    customer.setAddressLine1(rs.getString("address_line1"));
//                    customer.setAddressLine2(rs.getString("address_line2"));
//                    customer.setCity(rs.getString("city"));
//                    customer.setState(rs.getString("state"));
//                    customer.setStatus(rs.getString("status"));
//
//                    request.setAttribute("customer", customer);
//                    request.getRequestDispatcher("/view_customer.jsp").forward(request, response);
//                } else {
//                    request.setAttribute("errorMessage", "Customer not found.");
//                    request.getRequestDispatcher("/view_customer.jsp").forward(request, response);
//                }
//            }
//
//            // Fetch all customers
//            String allCustomersSql = "SELECT * FROM customer";
//            Statement stmt = con.createStatement();
//            ResultSet allCustomersRs = stmt.executeQuery(allCustomersSql);
//
//            List<Customer> allCustomers = new ArrayList<>();
//            while (allCustomersRs.next()) {
//                Customer cust = new Customer();
//                cust.setCustomerId(allCustomersRs.getString("customer_id"));
//                cust.setName(allCustomersRs.getString("name"));
//                cust.setAddressLine1(allCustomersRs.getString("address_line1"));
//                cust.setAddressLine2(allCustomersRs.getString("address_line2"));
//                cust.setCity(allCustomersRs.getString("city"));
//                cust.setState(allCustomersRs.getString("state"));
//                cust.setStatus(allCustomersRs.getString("status"));
//                allCustomers.add(cust);
//            }
//
//            request.setAttribute("allCustomers", allCustomers);
//            System.out.println("Total Customers Found: " + allCustomers.size());
//
//            // Forward to JSP
//            request.getRequestDispatcher("/view_customer.jsp").forward(request, response);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("errorMessage", "Error: " + e.getMessage());
//            request.getRequestDispatcher("/view_customer.jsp").forward(request, response);
//        }
//    }
//}
