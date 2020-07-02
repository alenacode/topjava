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

<div id="modalWin">
    <form class="transparent" method="POST">
        <div class="form-inner">
            <h3><span id="type">Запись</span><a href="meals?action=meals"><i class="icon fa fa-times"></i></a></h3>
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
    modal.style.display = "block";
</script>

</body>
</html>