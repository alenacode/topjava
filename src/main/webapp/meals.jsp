<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Meals</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="style/styles.css">
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
        <tr style="color:${meal.excess ? '#FFA500' : '#008000'}">
            <td>
                <fmt:parseDate value="${ meal.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${ parsedDateTime }"/>
            </td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><a href="meals?action=edit&datetime=<c:out value="${meal.dateTime}"/>&description=<c:out value="${meal.description}"/>&calories=<c:out value="${meal.calories}"/>" onclick="edit()"  style="color:${meal.excess ? '#FFA500' : '#008000'}"><i class="fa fa-pencil" aria-hidden="true"></i></a></td>
            <td><a href="meals?action=delete" style="color:${meal.excess ? '#FFA500' : '#008000'}"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<button class="btn1" onclick="add()">Добавить</button>

<div id="modalWin">
    <form class="transparent" method="POST">
        <div class="form-inner">
            <h3><span id="type"></span><a href="meals?action=meals"><i class="icon fa fa-times"></i></a></h3>
            <label for="datetime">Дата/время</label>
            <input id="datetime" type="datetime-local" max=now name="datetime" value="<c:out value="${meal.dateTime}" />">
            <label for="description">Описание</label>
            <input id="description" type="text" name="description" value="<c:out value="${meal.description}" />">
            <label for="calories">Калории</label>
            <input id="calories" type="number" name="calories" value="<c:out value="${meal.calories}" />">
            <button class="btn2">Сохранить</button>
        </div>
    </form>
</div>

<script>
    let modal = document.getElementById('modalWin');

    function add() {
        document.getElementById("type").innerHTML = "ДОБАВИТЬ";
        modal.style.display = "block";
    }

    function edit() {
        document.getElementById("type").innerHTML = "РЕДАКТИРОВАТЬ";
        modal.style.display = "block";
    }

    window.onclick = function(event) {
        if (event.target === modal)
            modal.style.display = "none";
    }
</script>

</body>
</html>