<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	
</head>
<body>
<%@ include file="header.jsp" %>
    <div class="container mt-5">
        <h2>Create Account</h2>
        
        <!-- Form to create an account -->
        <form action="CreateAccountServlet" method="post">
            <div class="mb-3">
                <label for="customerId" class="form-label">Customer ID</label>
                <input type="text" class="form-control" id="customerId" name="customerId" required>
            </div>
            <div class="mb-3">
                <label for="accountType" class="form-label">Account Type</label>
                <select class="form-control" id="accountType" name="accountType" required>
                    <option value="Savings">Savings</option>
                    <option value="Current">Current</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="initialDeposit" class="form-label">Initial Deposit</label>
                <input type="number" class="form-control" id="initialDeposit" name="initialDeposit" required>
            	<c:if test="${not empty errorDepositMessage}">
                    <span class="text-danger">${errorDepositMessage}</span>
                </c:if>
            </div>
            <button type="submit" class="btn btn-primary">Create Account</button>
        </form>

        <!-- Displaying success or error message -->
        <c:if test="${not empty errorMessage}">
        	<span class="text-danger">${errorMessage}</span>
        </c:if>

        <c:if test="${not empty successMessage}">
        	<span class="text-success">${successMessage}</span>
        </c:if>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
