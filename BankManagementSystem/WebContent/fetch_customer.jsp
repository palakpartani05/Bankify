<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fetch Customer Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Fetch Customer Details</h2>
        <form action="FetchCustomerServlet" method="get">
            <div class="mb-3">
                <label for="ssnId" class="form-label">SSN ID</label>
                <input type="text" class="form-control" id="ssnId" name="ssnId" required>
            </div>
            <button type="submit" class="btn btn-primary">Fetch Details</button>
        </form>
    </div>
</body>
</html>
