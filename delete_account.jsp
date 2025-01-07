<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	 <style>
        /* Consistent page-wide styling */
        body {
            background-color: #f8f9fa; /* Same light grey background */
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 1140px; /* Matches the Bootstrap default for large screens */
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin: auto; /* Center horizontally */
        }

        h2 {
            text-align: center;
            color: #0056b3;
            margin-bottom: 20px;
        }

        .form-container {
            max-width: 100px; /* Restricts form width */
            margin: 0 auto; /* Centers the form */
        }

        .form-label {
            font-weight: bold;
            color: #212529;
        }

        .btn-primary {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        .btn-primary:hover {
            background-color: #003d82;
            border-color: #003d82;
        }

        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }

        .btn-danger:hover {
            background-color: #a71d2a;
            border-color: #a71d2a;
        }

        input.form-control {
            height: 40px;
            border-radius: 5px;
            border: 1px solid #ced4da;
        }

        input.form-control:focus {
            border-color: #0056b3;
            box-shadow: 0 0 4px rgba(0, 86, 179, 0.5);
        }

        .alert {
            margin-top: 20px;
        }
    </style>

</head>
<body>
	<%@ include file="header.jsp" %>
    <div class="container mt-5">
        <h2>Delete Account</h2>
        
        <!-- Form to enter customer ID and search -->
        <form action="DeleteAccountServlet" method="post">
            <div class="mb-3">
                <label for="customerId" class="form-label">Customer ID</label>
                <input type="text" class="form-control" id="customerId" name="customerId" required>
            </div>
            <button type="submit" class="btn btn-primary" name="action" value="search">Search</button>
        </form>

        <!-- Displaying account details after search -->
        <c:if test="${not empty account}">
            <hr>
            <h4>Account Information</h4>
            <div class="mb-3">
                <label class="form-label">Account ID</label>
                <input type="text" class="form-control" value="${account.accountId}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Account Type</label>
                <input type="text" class="form-control" value="${account.accountType}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Balance</label>
                <input type="text" class="form-control" value="${account.balance}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Status</label>
                <input type="text" class="form-control" value="${account.status}" readonly>
            </div>
            <!-- Delete button only appears after search -->
            <form action="DeleteAccountServlet" method="post">
                <input type="hidden" name="customerId" value="${account.customerId}">
                <button type="submit" class="btn btn-danger" name="action" value="delete">Delete Account</button>
            </form>
        </c:if>

        <!-- Error or Success Message -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">
                ${errorMessage}
            </div>
        </c:if>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" role="alert">
                ${successMessage}
            </div>
        </c:if>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>

