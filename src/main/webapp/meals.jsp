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
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
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
<a href="meals?action=create"><button class="btn1">Добавить</button></a>
</body>
</html>