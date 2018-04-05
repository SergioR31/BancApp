<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
</head>
<body>
	<h1>BancApp</h1>
	
	<input type="button" onclick="location.href='/BancApp/Chequeras/listado'" value="Listado Chequeras">
	<input type="button" onclick="location.href='/BancApp/Chequeras/agregar'" value="Agregar Chequera">
	
	<input type="button" onclick="location.href='/BancApp/Movimientos/retiro'" value="Hacer Retiro">
	<input type="button" onclick="location.href='/BancApp/Movimientos/deposito'" value="Hacer Deposito">
	<input type="button" onclick="location.href='/BancApp/Movimientos/transferencia'" value="Hacer Transferencia">
	
	<input type="button" onclick="location.href='/BancApp/ConsultaMovimientos'" value="Consultas">
</body>
</html>