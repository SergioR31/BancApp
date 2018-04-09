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
<title>Listado Movimientos</title>
</head>
<body>
    <c:import url="/WEB-INF/fragments/boton-inicio.jsp"></c:import>
    <h1>${chequera.idChequera }</h1>
    <h2>${chequera.nombreBanco }</h2>
    <h2>${chequera.nombreCliente }</h2>
    <h2>${chequera.apellodoPCliente }</h2>
    <h2>Saldo Apertura: ${chequera.saldoApertura }</h2>
    <h2>Saldo Actual: ${chequera.saldo }</h2>
    <h2>Clabe: ${chequera.clabe }</h2>
    <h1>Depositos: ${totalDepositos }</h1>
    <h1>Retiros: ${totalRetiros }</h1>
    
	<c:forEach items="${movimientos }" var="movimiento">
	   <h1>${movimiento.idMovimiento }</h1>
	   <h2>${movimiento.concepto }</h2>
	   <h2>Monto: ${movimiento.monto }</h2>
	   <h2>${movimiento.fecha }</h2>
	   <h2>${movimiento.status }</h2>
	   <h2>${movimiento.operacion }</h2>
	   <h2>${movimiento.idTipo }</h2>
	   <h2>${movimiento.idChequera }</h2>
	   
	</c:forEach>
	
	<script>
	var mensaje = '${mensaje}';
	
	if (mensaje != null && mensaje != "") {
		alert('${mensaje}')
		
	}
	
	</script>
</body>
</html>