<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>View Customer</h2>
        
        <!-- Form to enter customer ID and search -->
        <form action="ViewCustomerServlet" method="post">
            <div class="mb-3">
                <label for="customerId" class="form-label">Customer ID</label>
                <input type="text" class="form-control" id="customerId" name="customerId">
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form><br>

        <!-- Error Message -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">
                ${errorMessage}
            </div>
        </c:if>

        <!-- Displaying customer details after search -->
        <c:if test="${not empty customer}">
            <hr>
            <h4>Customer Information</h4>
            <div class="mb-3">
                <label class="form-label">Customer ID</label>
                <input type="text" class="form-control" value="${customer.customerId}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Customer Name</label>
                <input type="text" class="form-control" value="${customer.name}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Address</label>
                <input type="text" class="form-control" value="${customer.addressLine1}, ${customer.addressLine2}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">City</label>
                <input type="text" class="form-control" value="${customer.city}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">State</label>
                <input type="text" class="form-control" value="${customer.state}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Status</label>
                <input type="text" class="form-control" value="${customer.status}" readonly>
            </div>
        </c:if>

        <!-- Displaying all registered customers -->
        <hr>
        <h4>All Registered Customers</h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cust" items="${allCustomers}">
                    <tr>
                        <td>${cust.customerId}</td>
                        <td>${cust.name}</td>
                        <td>${cust.addressLine1}, ${cust.addressLine2}</td>
                        <td>${cust.city}</td>
                        <td>${cust.state}</td>
                        <td>${cust.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
<%@ include file="footer.jsp" %>
