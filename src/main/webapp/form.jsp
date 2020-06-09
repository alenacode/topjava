<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="transparent" method="POST" action="meals?action=edit">
    <div class="form-inner">
        <h3>Запись<a href=""><i class="icon fa fa-times"></i></a></h3>
        <label for="datetime">Дата/время</label>
        <input id="datetime" type="datetime-local" name="datetime" value="<c:out value="${meal.dateTime}" />">
        <label for="description">Описание</label>
        <input id="description" type="text" name="description" value="<c:out value="${meal.description}" />">
        <label for="calories">Калории</label>
        <input id="calories" type="number" name="calories" value="<c:out value="${meal.calories}" />">
        <a href=""><button class="btn2">Сохранить</button></a>
    </div>
</form>
</body>
</html>
