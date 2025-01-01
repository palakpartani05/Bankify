package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/FetchCustomerServlet")
public class FetchCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ssnId = request.getParameter("ssnId");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM customer WHERE ssn_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ssnId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h2>Customer Details:</h2>");
                out.println("<p>SSN ID: " + rs.getString("ssn_id") + "</p>");
                out.println("<p>Name: " + rs.getString("name") + "</p>");
                out.println("<p>Age: " + rs.getInt("age") + "</p>");
                out.println("<p>Address: " + rs.getString("address_line1") + "</p>");
                out.println("<p>City: " + rs.getString("city") + "</p>");
                out.println("<p>State: " + rs.getString("state") + "</p>");
            } else {
                out.println("<p>No customer found with SSN ID: " + ssnId + "</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
