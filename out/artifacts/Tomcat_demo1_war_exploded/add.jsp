<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Employee</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>Add Employee Page</h3></center>
    <form action="${pageContext.request.contextPath}/addEmployeeServlet" method="post">
        <div class="form-group">
            <label for="firstName">First Name: </label>
            <input type="text" class="form-control" id="firstName" name="firstName">
        </div>

        <div class="form-group">
            <label for="lastName">Last Name: </label>
            <input type="text" class="form-control" id="lastName" name="lastName">
        </div>

        <div class="form-group">
            <label for="age">Age: </label>
            <input type="text" class="form-control" id="age" name="age">
        </div>

        <div class="form-group">
            <label for="salary">Salary: </label>
            <input type="text" class="form-control" id="salary" name="salary">
        </div>

        <div class="form-group">
            <label for="aptno">Apt.No: </label>
            <input type="text" class="form-control" id="aptno" name="aptno">
        </div>

        <div class="form-group">
            <label for="street">Street: </label>
            <input type="text" class="form-control" id="street" name="street">
        </div>

        <div class="form-group">
            <label for="city">City: </label>
            <input type="text" class="form-control" id="city" name="city">
        </div>

        <div class="form-group">
            <label for="state">State: </label>
            <input type="text" class="form-control" id="state" name="state">
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="submit" />
            <input class="btn btn-default" type="reset" value="reset" />
            <input class="btn btn-default" type="button" value="cancel" />
        </div>
    </form>
</div>
</body>
</html>
