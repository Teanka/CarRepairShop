<%--
  Created by IntelliJ IDEA.
  User: teanka
  Date: 05.11.18
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Report Two</title>
</head>
<body>
<%@ include file="header.jspx"%>
<form method="post" action="/reporttwo">
    <div class="form-group">
        <h4>Wybierz daty od:</h4>
        <input type="date" class="form-control" id="startDate" name="startDate" required>
        <h4>do:</h4>
        <input type="date" class="form-control" id="endDate" name="endDate" required>
    </div>
    <button type="submit" class="btn btn-primary">Raport zysków</button>

    <table border="1" class="table">
        <thead>
        <tr>
            <th scope="col">przychód</th>
            <th scope="col">koszt części</th>
            <th scope="col">pensje</th>
            <th scope="col">zysk</th>
        </tr>
        </thead>
        <tr>
            <td>${earnings}</td>
            <td>${partsCost}</td>
            <td>${employeeSalary}</td>
            <td>${profit}</td>
        </tr>
    </table>
</form>
<%@ include file="footer.jspx" %>
</body>
</html>
