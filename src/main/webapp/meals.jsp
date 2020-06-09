<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Meals</title>
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
            <td><a href="#modalWin" style="color:${meal.excess ? '#FFA500' : '#008000'}"><i class="fa fa-cogs" aria-hidden="true"></i></a></td>
            <td><a href="" style="color:${meal.excess ? '#FFA500' : '#008000'}"><i class="fa fa-times-circle" aria-hidden="true"></i></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="#modalWin"><button class="btn1">Добавить</button></a>


<div id="modalWin">
    <form class="transparent" action="meals" method="post">
        <div class="form-inner">
            <h3>Запись</h3>
            <a href=""><i class="icon fa fa-times fa-2x"></i></a>
            <label for="datetime">Дата/время</label>
            <input id="datetime" type="datetime-local">
            <label for="description">Описание</label>
            <input id="description" type="text">
            <label for="calories">Калории</label>
            <input id="calories" type="number">
            <a href=""><button class="btn2">Сохранить</button></a>
        </div>
    </form>
</div>


</body>
</html>