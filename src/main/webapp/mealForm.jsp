<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Meals</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
<div id="modalWin">
    <form class="transparent" method="POST" action="meals">
        <div class="form-inner">
            <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
            <input type="hidden" name="id" value="${meal.id}">
            <h3><span id="type">Запись</span><a href="meals"><i class="icon fa fa-times"></i></a></h3>
            <label for="datetime">Дата/время</label>
            <input id="datetime" type="datetime-local" name="dateTime" value="${meal.dateTime}">
            <label for="description">Описание</label>
            <input id="description" type="text" name="description" value="${meal.description}">
            <label for="calories">Калории</label>
            <input id="calories" type="number" name="calories" value="${meal.calories}">
            <button class="btn2">Сохранить</button>
        </div>
    </form>
</div>
</body>
</html>