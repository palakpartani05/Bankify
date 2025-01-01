<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validateForm() {
            let isValid = true;
            
            // Validate SSN ID
            const ssnId = document.getElementById("ssnId");
            const ssnPattern = /^[0-9]{9}$/;
            if (!ssnPattern.test(ssnId.value)) {
                document.getElementById("ssnError").innerText = "SSN must be 9 digits";
                isValid = false;
            } else {
                document.getElementById("ssnError").innerText = "";
            }

            // Validate customer name
            const customerName = document.getElementById("customerName");
            if (customerName.value === "") {
                document.getElementById("nameError").innerText = "Customer Name is required";
                isValid = false;
            } else {
                document.getElementById("nameError").innerText = "";
            }

            // Validate age
            const age = document.getElementById("age");
            if (age.value <= 0 || age.value >= 120) {
                document.getElementById("ageError").innerText = "Age must be between 1 and 120";
                isValid = false;
            } else {
                document.getElementById("ageError").innerText = "";
            }

            // Validate address line1
            const addressLine1 = document.getElementById("addressLine1");
            if (addressLine1.value === "") {
                document.getElementById("addressLine1Error").innerText = "Address Line 1 is required";
                isValid = false;
            } else {
                document.getElementById("addressLine1Error").innerText = "";
            }

            // Validate city
            const city = document.getElementById("city");
            if (city.value === "") {
                document.getElementById("cityError").innerText = "City is required";
                isValid = false;
            } else {
                document.getElementById("cityError").innerText = "";
            }

            // Validate state
            const state = document.getElementById("state");
            if (state.value === "") {
                document.getElementById("stateError").innerText = "State is required";
                isValid = false;
            } else {
                document.getElementById("stateError").innerText = "";
            }

            return isValid;
        }
    </script>
</head>
<body>
 <!-- Displaying success or error message on the same page -->
        

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success mt-3" role="alert">
                ${successMessage}
            </div>
        </c:if>
    <div class="container mt-5">
        <h2>Create Customer</h2>
        <form action="CustomerRegistrationServlet" method="post" onsubmit="return validateForm()">
            <div class="mb-3">
                <label for="ssnId" class="form-label">SSN ID</label>
                <input type="text" class="form-control" id="ssnId" name="ssnId" required>
                <div id="ssnError" class="text-danger"></div>
            	<c:if test="${not empty errorMessage}">
		        	<span class="text-danger">${errorMessage}</span>
		        </c:if>
            </div>
            <div class="mb-3">
                <label for="customerName" class="form-label">Customer Name</label>
                <input type="text" class="form-control" id="customerName" name="customerName" required>
                <div id="nameError" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <label for="age" class="form-label">Age</label>
                <input type="number" class="form-control" id="age" name="age" required>
                <div id="ageError" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <label for="addressLine1" class="form-label">Address Line 1</label>
                <input type="text" class="form-control" id="addressLine1" name="addressLine1" required>
                <div id="addressLine1Error" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <label for="addressLine2" class="form-label">Address Line 2</label>
                <input type="text" class="form-control" id="addressLine2" name="addressLine2">
            </div>
            <div class="mb-3">
                <label for="city" class="form-label">City</label>
                <input type="text" class="form-control" id="city" name="city" required>
                <div id="cityError" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <label for="state" class="form-label">State</label>
                <input type="text" class="form-control" id="state" name="state" required>
                <div id="stateError" class="text-danger"></div>
            </div>
            <button type="submit" class="btn btn-primary">Create Customer</button>
        </form>
       
    </div>
</body>
</html>
