<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: teanka
  Date: 01.11.18
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers CRUD</title>
    <meta charset="utf-8">


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<%@ include file="header.jspx" %>
Aktualni klienci warsztatu:
<table border="1" class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">IMIĘ</th>
        <th scope="col">NAZWISKO</th>
        <th scope="col">DATA URODZENIA</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.firstName}</td>
            <td>${item.lastName}</td>
            <td>${item.birthDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>Dodaj nowego klienta do bazy:</h3>
<form method="POST" action="/customer">
    <div class="form-group">
        <input type="hidden" name="id" value=${customer.id}>
        <input type="text" class="form-control" placeholder="Imię" name="firstName" required>
        <input type="text" class="form-control" placeholder="Nazwisko klienta" name="lastName" required>
        Data urodzenia: *opcjonalnie*<input type="date" class="form-control" placeholder="Data urodzenia w formacie yyyy-MM-dd (opcjonalnie)" name="birthDate" >

    </div>
    <button type="submit" class="btn btn-primary">Dodaj klienta</button>
</form>

<h3>Podaj numer id klienta do usunięcia z bazy:</h3>
<form method = "POST" action="/customer">
    <div class="form-group">
        <input type="number"  placeholder="Numer id klienta do wykasowania z bazy" name="idDel" required>
    </div>
    <button type="submit" class="btn btn-primary">Usuń klienta</button>
</form>


<h3>Podaj numer id klienta do edycji w bazie i zmień jego dane:</h3>
<form method = "POST" action="/customer">
    <div class="form-group">
        <input type="number"  placeholder="Numer id klienta do wykasowania z bazy" name="idEdit" required>
        <input type="text" class="form-control" placeholder="Imię" name="firstNameEd" required>
        <input type="text" class="form-control" placeholder="Nazwisko klienta" name="lastNameEd" required>
        Data urodzenia: *opcjonalnie*<input type="date" class="form-control" placeholder="Data urodzenia w formacie yyyy-MM-dd (opcjonalnie)" name="birthDateEd" >
    </div>
    <button type="submit" class="btn btn-primary">Edytuj klienta</button>
</form>

<h2><a href="/employee">Przejdź do zarządzania pracownikami</a></h2>
<h2><a href="/vehicle">Przejdź do przeglądania samochodów</a></h2>
<h2><a href="/orders">Przejdź do zamówień</a></h2>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<%@ include file="footer.jspx" %>
</body>
</html>
