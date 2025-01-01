<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Delete Customer</h2>
        
        <!-- Form to enter customer ID and search -->
        <form action="FetchCustomerForDeleteServlet" method="post">
            <div class="mb-3">
                <label for="customerId" class="form-label">Customer ID</label>
                <input type="text" class="form-control" id="customerId" name="customerId" required>
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <!-- Displaying customer details after search -->
        <c:if test="${not empty customer}">
            <hr>
            <h4>Customer Information</h4>
            <form action="DeleteCustomerServlet" method="post">
                <input type="hidden" name="customerId" value="${customer.customerId}">
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
                <button type="submit" class="btn btn-danger">Delete Customer</button>
            </form>
        </c:if>
    </div>
</body>
</html>


<%@ include file="footer.jsp" %>
