<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Meals</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <tr>
        <th>Дата/время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr style="color:${meal.excess ? '#FFA500' : '#008000'}">
            <td>
                <fmt:parseDate value="${ meal.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${ parsedDateTime }"/>
            </td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>