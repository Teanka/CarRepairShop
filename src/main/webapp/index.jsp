<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: teanka
  Date: 01.11.18
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<%@ include file="header.jspx"%>

<h3>Aktualne naprawy:</h3>
<table border="1" class="table">
    <thead>
    <tr>
        <th scope="col">ID naprawy</th>
        <th scope="col">Data rozpoczęcia naprawy</th>
        <th scope="col">Opis problemu</th>
        <th scope="col">ID Pracownika</th>
        <th scope="col">Szczegóły naprawy</th>
    </tr>
    </thead>
    <c:forEach items="${currentOrders}" var="repair">
        <tr>
            <td>${order.id}</td>
            <td>${order.start_date}</td>
            <td>${order.problem_desc}</td>
            <td>${order.employeeId}</td>
            <td><a href="/orderDetails?id=${order.id}">Szczegółowe dane</a></td>
        </tr>
    </c:forEach>

    <h4><a href="/employee">Przejdź do zarządzania pracownikami</a></h4>
    <h2><a href="/customer">Przejdź do zarządzania klientami</a></h2>
</table>
<%@ include file="footer.jspx"%>
</body>
</html>

<%--Utwórz pliki header.jsp, footer.jsp oraz plik index.jsp, który będzie je załączał – w ten sposób stworzymy szablon naszej aplikacji.--%>
<%--W pliku header.jsp umieścimy linki nawigacyjne naszej aplikacji.--%>
<%--W pliku footer.jsp umieścimy stopkę informacyjną.--%>
<%--W metodzie doGet pierwszego servletu pobierz przy pomocy odpowiedniej metody klasy DAO listę aktualnie prowadzonych napraw.--%>
<%--Przekaż pobraną listę do widoku index.jsp, a następnie wyświetl szczegóły zleceń w wierszach tabeli html.--%>

<%--Zadanie 2--%>

<%--Widoki możesz stworzyć z wykorzystaniem biblioteki Bootstrap.--%>

<%--Bootstrap znacznie poprawia wygląd strony a jego użycie jest bardzo proste.--%>

<%--Jeżeli również chcecie skorzystać z bootstrapa odsyłam do następujących stron:--%>

<%--http://getbootstrap.com/--%>
<%--https://www.w3schools.com/bootstrap/--%>
<%--https://kursbootstrap.pl/--%>



