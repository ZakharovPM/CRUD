<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Редактирование пользователя</title>
</head>
<body>
<h1>Редактирование пользователя</h1>
<p>Для редактирования пользователя перезаполните поля ниже</p>
<p>${message}</p>
<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/edit/${user.id}.html">
<table>
<tbody>
	<tr>
		<td>Имя:</td>
		<td><form:input path="name" maxlength="25" required="yes" placeholder="До 25 знаков." /></td>
	</tr>
	<tr>
		<td>Возраст:</td>
		<td><form:input path="age" type="number" max="120" min="0" required="yes" placeholder="0 - 120."/></td>
	</tr>
	<tr>
		<td>Является администратором:</td>
		<td><form:checkbox path="isAdmin" /></td>
	</tr>
	<tr>
		<td>
			<input type="submit" formaction="${pageContext.request.contextPath}/list.html" value="Назад к списку"/>
		</td>
		<td><input type="submit" value="Отредактировать" /></td>

	</tr>
</tbody>
</table>
</form:form>




</body>
</html>