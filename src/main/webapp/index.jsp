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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


<h3>Aktualne naprawy:</h3>
<table border="1" class="table">
    <thead>
    <tr>
        <th scope="col">ID NAPRAWY</th>
        <th scope="col">DATA ROZPOCZĘCIA NAPRAWY</th>
        <th scope="col">OPIS PROBLEMU OD KLIENTA</th>
        <th scope="col">ID PRACOWNIKA</th>
        <th scope="col">ID SAMOCHODU</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${currentOrders}" var="order">

        <tr>
            <td>${order.id}</td>
            <td>${order.startDate}</td>
            <td>${order.orderDescription}</td>
            <td>${order.employeeId}</td>
            <td>${order.vehicleId}</td>
        </tr>

    </c:forEach>
    </tbody>
</table>
<h2><a href="/employee">Przejdź do zarządzania pracownikami</a></h2>
<h2><a href="/customer">Przejdź do zarządzania klientami</a></h2>
<h2><a href="/vehicle">Przejdź do zarządzania samochodami</a></h2>
<h2><a href="/orders">Przejdź do zamówień</a></h2>
<h2><a href="/reportone">Przejdź do raportu roboczogodzin</a></h2>
<h2><a href="/reporttwo">Przejdź do raportu zysków</a></h2>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<%@ include file="footer.jspx"%>
</body>
</html>
<%--SELECT id, received, start_date, employee_id, order_description, vehicle_id FROM orders WHERE status = ?--%>
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



