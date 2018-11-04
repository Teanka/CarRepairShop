<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: teanka
  Date: 04.11.18
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders CRUD</title>
</head>
<body>
<%@ include file="header.jspx" %>
<h4>Lista napraw: </h4><br>
<table border="1" class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">ZAMÓWIENIE OTRZYMANO</th>
        <th scope="col">PLANOWANY POCZĄTEK NAPRAWY</th>
        <th scope="col">POCZĄTEK NAPRAWY</th>
        <th scope="col">KONIEC NAPRAWY</th>
        <th scope="col">ID PRACOWNIKA</th>
        <th scope="col">OPIS OD KLIENTA</th>
        <th scope="col">OPIS NAPRAWY</th>
        <th scope="col">STATUS</th>
        <th scope="col">NUMER ID SAMOCHODU</th>
        <th scope="col">KOSZT KLIENTA</th>
        <th scope="col">KOSZT CZĘŚCI</th>
        <th scope="col">ROBOCZOGODZINA</th>
        <th scope="col">ILOŚĆ GODZIN</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ordersList}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.received}</td>
            <td>${item.plannedRepairDate}</td>
            <td>${item.startDate}</td>
            <td>${item.endDate}</td>
            <td>${item.employeeId}</td>
            <td>${item.orderDescription}</td>
            <td>${item.repairDescription}</td>
            <td>${item.status}</td>
            <td>${item.vehicleId}</td>
            <td>${item.customerCost}</td>
            <td>${item.partsCost}</td>
            <td>${item.manHour}</td>
            <td>${item.hoursTotal}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>Dodaj nowe zamówienie:</h3>
<form method="POST" action="/orders">
    <div class="form-group">

        <input type="hidden" name="id" value=${order.id}>
        Otrzymane: <input type="date" class="form-control" placeholder="otrzymane" name="received" required>
        Planowany początek naprawy: <input type="date" class="form-control" placeholder="planowany początek naprawy"
                                           name="planned" required>
        Początek naprawy: <input type="date" class="form-control" placeholder="początek naprawy" name="start">
        Koniec naprawy: <input type="date" class="form-control" placeholder="" name="end">
        <input type="number" class="form-control" placeholder="nr pracownika" name="employeeId" required>
        <br>
        Opis zamówienia:
        <textarea name="orderDescription" id="orderD" cols="30" rows="10" required></textarea>
        Opis naprawy:
        <textarea name="repairDescription" id="repairD" cols="30" rows="10" required></textarea>
        <br>
        Status naprawy(wybierz pole):<select id="status" name="status">
        <option value="Przyjęty">Przyjęty</option>
        <option value="Koszty_zatwierdzone">Koszty_zatwierdzone</option>
        <option value="W_naprawie">W_naprawie</option>
        <option value="Gotowy_do_odbioru">Gotowy_do_odbioru</option>
        <option value="Rezygnacja">Rezygnacja</option>
    </select>
        <input type="number" class="form-control" placeholder="id samochodu" name="vehicleId" required>
        <input type="number" class="form-control" placeholder="koszt klienta" name="customerCost">
        <input type="number" class="form-control" placeholder="koszt części" name="partsCost">
        <input type="number" class="form-control" placeholder="ilość godzin" name="hours">
    </div>
    <button type="submit" class="btn btn-primary">Dodaj samochód</button>
</form>

<h3>Podaj numer zamówienia do usunięcia z bazy:</h3>
<form method="POST" action="/orders">
    <div class="form-group">
        <input type="number" placeholder="Numer zamówienia do wykasowania z bazy" name="idDel" required>
    </div>
    <button type="submit" class="btn btn-primary">Usuń zamówienie</button>
</form>

<h3>Edytuj istniejące zamówienie:</h3>
<form method="POST" action="/orders">
    <div class="form-group">

        Id zamówienia:<input type="number" class="form-control" placeholder="id zamówienia" name="idEdit" required>
        Otrzymane: <input type="date" class="form-control" placeholder="otrzymane" name="receivedEd" required>
        Planowany początek naprawy: <input type="date" class="form-control" placeholder="planowany początek"
                                           name="plannedEd" required>
        Początek naprawy: <input type="date" class="form-control" placeholder="Początek naprawy" name="startEd"
                                 required>
        Koniec naprawy: <input type="date" class="form-control" placeholder="" name="endEd">
        <input type="number" class="form-control" placeholder="nr pracownika" name="employeeIdEd" required>
        <br>
        Opis zamówienia:
        <textarea name="orderDescriptionEd" id="orderDescription" cols="30" rows="10" required></textarea>
        Opis naprawy:
        <textarea name="repairDescriptionEd" id="repairDescription" cols="30" rows="10" required></textarea>
        <br>
        Status naprawy(wybierz pole):<select id="statusEd" name="statusEd">
        <option value="Przyjęty">Przyjęty</option>
        <option value="Koszty_zatwierdzone">Koszty_zatwierdzone</option>
        <option value="W_naprawie">W_naprawie</option>
        <option value="Gotowy_do_odbioru">Gotowy_do_odbioru</option>
        <option value="Rezygnacja">Rezygnacja</option>
    </select>
        <input type="number" class="form-control" placeholder="id samochodu" name="vehicleIdEd" required>
        <input type="number" class="form-control" placeholder="koszt klienta" name="customerCostEd">
        <input type="number" class="form-control" placeholder="koszt części" name="partsCostEd">
        <input type="number" class="form-control" placeholder="ilość godzin" name="hoursEd">
    </div>
    <button type="submit" class="btn btn-primary">Dodaj samochód</button>
</form>
<h2><a href="/employee">Przejdź do zarządzania pracownikami</a></h2>
<h2><a href="/customer">Przejdź do listy klientów</a></h2>
<h2><a href="/vehicle">Przejdź do listy samochodów</a></h2>
<%@ include file="footer.jspx" %>
</body>
</html>

<%--//    MySQL status enum('Przyjęty', 'Koszty_zatwierdzone', 'W_naprawie', 'Gotowy_do_odbioru', 'Rezygnacja')--%>