<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Meals</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <tr>
        <th>Дата/время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
        <tr style="color:${meal.excess ? '#FFA500' : '#008000'}">
            <td>
                <fmt:parseDate value="${ meal.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="HH:mm dd-MM-yyyy" value="${ parsedDateTime }"/>
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}" style="color:${meal.excess ? '#FFA500' : '#008000'}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
            <td><a href="meals?action=delete&id=${meal.id}" style="color:${meal.excess ? '#FFA500' : '#008000'}"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<button class="btn3" id="filterBtn">Отфильтровать</button>
<a href="meals?action=create"><button class="btn1">Добавить</button></a>

<div id="modalWinFilter">
    <form class="transparent" method="GET" action="meals?action=read">
        <div class="form-inner">
            <h3><span id="type">Фильтр</span><a href="meals?action=read"><i class="icon fa fa-times"></i></a></h3>

            <label for="dateFrom">Дата ОТ</label>
            <input id="dateFrom" type="date" name="dateFrom">

            <label for="dateTo">Дата ДО</label>
            <input id="dateTo" type="date" name="dateTo">

            <label for="timeFrom">Время ОТ</label>
            <input id="timeFrom" type="time" name="timeFrom">

            <label for="timeTo">Время ДО</label>
            <input id="timeTo" type="time" name="timeTo">

            <button class="btn2">Отфильтровать</button>
        </div>
    </form>
</div>

<script>
    let modal = document.getElementById('modalWinFilter');
    let btn = document.getElementById('filterBtn');

    btn.onclick = function () {
        modal.style.display = "block";
    }

    window.onclick = function(event) {
        if (event.target === modal)
            modal.style.display = "none";
    }
</script>

</body>
</html>