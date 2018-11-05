<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: teanka
  Date: 04.11.18
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Report One</title>
</head>
<body>
<%@ include file="header.jspx"%>
<form method="POST" action="/reportone">
    <div class="form-group">
        <h4>Wybierz daty: od</h4>
        <input type="date" class="form-control" id="startDate" name="startDate" required>
        <h4>do</h4>
        <input type="date" class="form-control" id="endDate" name="endDate" required>
    </div>
    <button type="submit" class="btn btn-primary">Utwórz raport</button>
</form>

<c:if test="${hours!=null}">
    <h3>Roboczogodziny:</h3>
    <table border="1" class="table">
        <thead>
        <tr>
            <th scope="col">Pracownik</th>
            <th scope="col">Ilość roboczogodzin</th>
        </tr>
        </thead>
        <c:forEach items="${hours}" var="employee">
            <tr>
                <td>${employee.key}</td>
                <td>${employee.value}</td>
            </tr>
        </c:forEach>
    </table>

</c:if>
<%@ include file="footer.jspx" %>
</body>
</html>
