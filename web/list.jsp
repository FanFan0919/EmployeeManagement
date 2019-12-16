<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employees Management System</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteEmployee(id){
            if(confirm("Confirm to Delete?")){
                location.href="${pageContext.request.contextPath}/delEmployeeServlet?id="+id;
            }
        }

        window.onload = function () {
            document.getElementById("delSelected").onclick = function(){
                if(confirm("Confirm to Delete?")){
                    // check if any checkbox is selected
                    var flag = false;
                    var cbs = document.getElementsByName("eid");
                    for (var i = 0; i < cbs.length; i++) {
                        if(cbs[i].checked) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        document.getElementById("form").submit();
                    }
                }
            }

            //
            document.getElementById("firstCb").onclick = function(){
                var cbs = document.getElementsByName("eid");
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">Employees Information List</h3>

    <%--Search Button--%>
<%--    <div style="float: left;">--%>
<%--        <form class="form-inline">--%>
<%--            <div class="form-group">--%>
<%--                <label for="firstName">FirstName</label>--%>
<%--                <input type="text" class="form-control" id="firstName">--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label for="lastName">LastName</label>--%>
<%--                <input type="text" class="form-control" id="lastName">--%>
<%--            </div>--%>
<%--            <button type="submit" class="btn btn-default">Search</button>--%>
<%--        </form>--%>
<%--    </div>--%>

    <%--Add and Delete Button--%>
    <div style="float: right;margin: 5px;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">Add Employees</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">Delete Employees</a>
    </div>

    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
    <%--Table Head--%>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>id</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Age</th>
            <th>Salary</th>
            <th>Apt.No</th>
            <th>Street</th>
            <th>City</th>
            <th>State</th>
            <th>Management</th>
        </tr>
        <%--Table Content--%>
        <c:forEach items="${pb.list}" var="employee" varStatus="s">
            <tr>
                <td><input type="checkbox" name="eid" value="${employee.id}"></td>
                <td>${employee.id}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.age}</td>
                <td>${employee.salary}</td>
                <td>${employee.aptno}</td>
                <td>${employee.street}</td>
                <td>${employee.city}</td>
                <td>${employee.state}</td>
                <td>
<%--                <a class="btn btn-default btn-sm" href="update.html">Modify</a>&nbsp;--%>
                    <a class="btn btn-default btn-sm" href="javascript:deleteEmployee(${employee.id});">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </form>
<%--    Page partition--%>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="${pageContext.request.contextPath}/findEmployeeByPageServlet?currentPage=${pb.currentPage-1}&rows=10" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">

                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findEmployeeByPageServlet?currentPage=${i}&rows=10">${i}</a></li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/findEmployeeByPageServlet?currentPage=${i}&rows=10">${i}</a></li>
                    </c:if>

                </c:forEach>

                <li>
                    <a href="${pageContext.request.contextPath}/findEmployeeByPageServlet?currentPage=${pb.currentPage+1}&rows=10" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;"> ALL ${pb.totalCount} Employees, ${pb.totalPage} Pages</span>
            </ul>
        </nav>
    </div>

</div>
</body>
</html>
