<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: teanka
  Date: 03.11.18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD vehicles</title>
</head>
<body>
<%@ include file="header.jspx" %>
<h4>Samochody wybranego klienta: </h4><br>
<h5>Podaj numer id klienta, którego samochody chcesz obejrzeć <br>
    (wpisz "0" jeśli chcesz obejrzeć wszystkie samochody dostępne w bazie)</h5>
</br>
<h3>Podaj numer id klienta:</h3>
<form method = "POST" action="/vehicle">
    <div class="form-group">
        <input type="number"  placeholder="Numer id klienta " name="customerId" required>
    </div>
    <button type="submit" class="btn btn-primary">Sprawdź auta klienta</button>
</form>
<table border="1" class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">MARKA</th>
        <th scope="col">MODEL</th>
        <th scope="col">DATA PRODUKCJI</th>
        <th scope="col">NUMER REJESTRACYJNY</th>
        <th scope="col">DATA KOLEJNEGO PRZEGLĄDU</th>
        <th scope="col">NUMER ID WŁAŚCICIELA</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customerVehicles}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.brand}</td>
            <td>${item.model}</td>
            <td>${item.productionDate}</td>
            <td>${item.registrationNo}</td>
            <td>${item.nextInspection}</td>
            <td>${item.customerId}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>Dodaj nowy samochód do bazy:</h3>
<form method="POST" action="/vehicle">
    <div class="form-group">

        <input type="hidden" name="id" value=${vehicle.id}>
        <input type="text" class="form-control" placeholder="marka" name="brand" required>
        <input type="text" class="form-control" placeholder="model" name="model" required>
        <input type="date" class="form-control" placeholder="Data produkcji" name="productionDate" required>
        <input type="text" class="form-control" placeholder="nr rejestracyjny" name="registrationNo" required>
        <input type="date" class="form-control" placeholder="Data przeglądu" name="nextInspection" required>
        <input type="number" class="form-control" placeholder="nr klienta" name="customerIdAdd" required>

    </div>
    <button type="submit" class="btn btn-primary">Dodaj samochód</button>
</form>

<h3>Podaj numer id samochodu do usunięcia z bazy:</h3>
<form method = "POST" action="/vehicle">
    <div class="form-group">
        <input type="number"  placeholder="Numer id klienta do wykasowania z bazy" name="idDel" required>
    </div>
    <button type="submit" class="btn btn-primary">Usuń samochód</button>
</form>


<h3>Podaj numer id samochodu do edycji w bazie i zmień jego dane:</h3>
<form method = "POST" action="/vehicle">
    <div class="form-group">
        <input type="number" class="form-control" placeholder="id" name="idEdit" required>
        <input type="text" class="form-control" placeholder="marka" name="brandEd" required>
        <input type="text" class="form-control" placeholder="model" name="modelEd" required>
        <input type="date" class="form-control" placeholder="Data produkcji" name="productionDateEd" required>
        <input type="text" class="form-control" placeholder="nr rejestracyjny" name="registrationNoEd" required>
        <input type="date" class="form-control" placeholder="Data przeglądu" name="nextInspectionEd" required>
        <input type="number" class="form-control" placeholder="nr klienta" name="customerIdEd" required>
    </div>
    <button type="submit" class="btn btn-primary">Edytuj samochód</button>
</form>

<h2><a href="/employee">Przejdź do zarządzania pracownikami</a></h2>
<h2><a href="/customer">Przejdź do listy klientów</a></h2>
<%@ include file="footer.jspx" %>

</body>
</html>
