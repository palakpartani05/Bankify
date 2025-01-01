<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validateForm() {
            let isValid = true;
            
            // Validate customer ID
            const customerId = document.getElementById("customerId");
            if (customerId.value === "") {
                document.getElementById("idError").innerText = "Customer ID is required";
                isValid = false;
            } else {
                document.getElementById("idError").innerText = "";
            }

            // Validate customer name
            const customerName = document.getElementById("customerName");
            if (customerName.value === "") {
                document.getElementById("nameError").innerText = "Customer Name is required";
                isValid = false;
            } else {
                document.getElementById("nameError").innerText = "";
            }

            return isValid;
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2>Update Customer</h2>
        <form action="UpdateCustomerServlet" method="post" onsubmit="return validateForm()">
            <div class="mb-3">
                <label for="customerId" class="form-label">Customer ID</label>
                <input type="text" class="form-control" id="customerId" name="customerId" required>
                <div id="idError" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <label for="customerName" class="form-label">Customer Name</label>
                <input type="text" class="form-control" id="customerName" name="customerName" required>
                <div id="nameError" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <label for="addressLine1" class="form-label">Address Line 1</label>
                <input type="text" class="form-control" id="addressLine1" name="addressLine1">
                <div id="addressLine1Error" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <label for="addressLine2" class="form-label">Address Line 2</label>
                <input type="text" class="form-control" id="addressLine2" name="addressLine2">
            </div>
            <div class="mb-3">
                <label for="city" class="form-label">City</label>
                <input type="text" class="form-control" id="city" name="city">
                <div id="cityError" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <label for="state" class="form-label">State</label>
                <input type="text" class="form-control" id="state" name="state">
                <div id="stateError" class="text-danger"></div>
            </div>
            <button type="submit" class="btn btn-primary">Update Customer</button>
        </form>
    </div>
</body>
</html>
