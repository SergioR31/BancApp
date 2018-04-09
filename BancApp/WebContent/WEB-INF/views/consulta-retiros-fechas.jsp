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
<title>Listado Retiros por Fechas</title>
</head>
<body>
    <c:import url="/WEB-INF/fragments/boton-inicio.jsp"></c:import>
    <h1>${chequera.idChequera }</h1>
    <h2>${chequera.nombreBanco }</h2>
    <h2>${chequera.nombreCliente }</h2>
    <h2>${chequera.apellodoPCliente }</h2>
    <h2>Consulta ${periodo }</h2>
    <h2>${anio }</h2>
    <h2>${mes }</h2>
    <h1>${totalDepositos }</h1>
	<c:forEach items="${depositos }" var="deposito">
	   <h1>${deposito.idMovimiento }</h1>
	   <h2>${deposito.concepto }</h2>
	   <h2>${deposito.monto }</h2>
	   <h2>${deposito.fecha }</h2>
	   <h2>${deposito.status }</h2>
	   <h2>${deposito.idTipo }</h2>
	   <h2>${deposito.idChequera }</h2>
       <h2>${deposito.saldo }</h2>
	   
	</c:forEach>
	
	<script>
	var mensaje = '${mensaje}';
	
	if (mensaje != null && mensaje != "") {
		alert('${mensaje}')
		
	}
	
	</script>
</body>
</html>