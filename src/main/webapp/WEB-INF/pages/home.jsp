<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Результат операции</title>

</head>
<body>
<h2>Результат операции:</h2>
<p>
<h3>${message}<br/></h3>

<hr/>
<br/>


<form action="${pageContext.request.contextPath}/result.html">
    <input type="submit" value="Назад к последним результатам поиска"/></form>
<br/>

<form method="GET" action="${pageContext.request.contextPath}/add.html">
    <input type="submit" value="Добавить нового пользователя"/></form>
<br/>

<form action="${pageContext.request.contextPath}/list.html">
    <input type="submit" value="Главная страница"/></form>





</p>
</body>
</html>