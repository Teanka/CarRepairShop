<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: teanka
  Date: 01.11.18
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>employees CRUD</title>
</head>
<body>
<%@ include file="header.jspx"%>
<h3>Aktualni pracownicy warsztatu:</h3>
<table border="1" class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">IMIĘ</th>
        <th scope="col">NAZWISKO</th>
        <th scope="col">ADRES</th>
        <th scope="col">TELEFON</th>
        <th scope="col">NOTATKI</th>
        <th scope="col">ROBOCZOGODZINA</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.firstName}</td>
            <td>${item.lastName}</td>
            <td>${item.address}</td>
            <td>${item.phone}</td>
            <td>${item.note}</td>
            <td>${item.manHour}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>Dodaj nowego pracownika do bazy:</h3>
<form method = "POST" action="/employee">
    <div class="form-group">
        <input type="hidden"  name= "id" value=${employee.id}>
        <input type="text" class="form-control"  placeholder="Imię" name="firstName" required>
        <input type="text" class="form-control"  placeholder="Nazwisko" name="lastName" required>
        <input type="text" class="form-control"  placeholder="Adres" name="address" required>
        <input type="text" class="form-control"  placeholder="Telefon" name="phone" required>
        <input type="text" class="form-control" placeholder="Notka" name="note" >
        <input type="number" class="form-control" placeholder="Roboczogodzina" name="manHour" required>
    </div>
    <button type="submit" class="btn btn-primary">Dodaj pracownika</button>
    <br>
</form>
<br>
<h3>Podaj numer id pracownika do usunięcia z bazy:</h3>
<form method = "POST" action="/employee">
    <div class="form-group">
        <input type="number"  placeholder="Numer id pracownika do wykasowania z bazy" name="idDel" required>
    </div>
    <button type="submit" class="btn btn-primary">Usuń pracownika</button>
</form>
<br>
<h3>Podaj numer id pracownika i zmień jego dane:</h3>
<form method = "POST" action="/employee">
    <div class="form-group">
        <input type="number"  placeholder="Id do edycji" name="idEdit" required>
        <div class="form-group">
            <input type="hidden"  name= "id" value=${employee.id}>
            <input type="text" class="form-control"  placeholder="Imię" name="firstNameEd" required>
            <input type="text" class="form-control"  placeholder="Nazwisko" name="lastNameEd" required>
            <input type="text" class="form-control"  placeholder="Adres" name="addressEd" required>
            <input type="text" class="form-control"  placeholder="Telefon" name="phoneEd" required>
            <input type="text" class="form-control" placeholder="Notka" name="noteEd" >
            <input type="number" class="form-control" placeholder="Roboczogodzina" name="manHourEd" required>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Edytuj pracownika</button>
</form>
<h2><a href="/customer">Przejdź do zarządzania klientami</a></h2>
<h2><a href="/vehicle">Przejdź do widoku pojazdów</a></h2>
<h2><a href="/orders">Przejdź do zamówień</a></h2>
<%@ include file="footer.jspx"%>
</body>
</html>
<%--Zadanie 3--%>
<%--Lista pracowników--%>

<%--Utwórz servlet oraz widok w postaci pliku jsp.--%>

<%--Wykorzystując klasę DAO pobierz a następnie przekaż do widoku listę wszystkich pracowników .--%>

<%--Utwórz pozostałe akcje i widoki:--%>

<%--dodawanie pracownika--%>
<%--edycja pracownika--%>
<%--usuwanie pracownika--%>
<%--zlecenia pracownika--%>