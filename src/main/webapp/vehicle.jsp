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
    <title>vehicles CRUD</title>
</head>
<body>
<%@ include file="header.jspx" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

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
        data produkcji: <input type="date" class="form-control" placeholder="Data produkcji" name="productionDate" required>
        <input type="text" class="form-control" placeholder="nr rejestracyjny" name="registrationNo" required>
        następny przegląd: <input type="date" class="form-control" placeholder="Data przeglądu" name="nextInspection" required>
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
        data produkcji: <input type="date" class="form-control" placeholder="Data produkcji" name="productionDateEd" required>
        <input type="text" class="form-control" placeholder="nr rejestracyjny" name="registrationNoEd" required>
        następny przegląd: <input type="date" class="form-control" placeholder="Data przeglądu" name="nextInspectionEd" required>
        <input type="number" class="form-control" placeholder="nr klienta" name="customerIdEd" required>
    </div>
    <button type="submit" class="btn btn-primary">Edytuj samochód</button>
</form>

<h2><a href="/employee">Przejdź do zarządzania pracownikami</a></h2>
<h2><a href="/customer">Przejdź do listy klientów</a></h2>
<h2><a href="/orders">Przejdź do zamówień</a></h2>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<%@ include file="footer.jspx" %>

</body>
</html>
