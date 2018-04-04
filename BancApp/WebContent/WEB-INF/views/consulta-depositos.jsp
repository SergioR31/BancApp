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
<title>Listado Depositos</title>
</head>
<body>
    <h1>${totalDepositos }</h1>
	<c:forEach items="${depositos }" var="deposito">
	   <h1>${deposito.idMovimiento }</h1>
	   <h2>${deposito.concepto }</h2>
	   <h2>${deposito.monto }</h2>
	   <h2>${deposito.fecha }</h2>
	   <h2>${deposito.status }</h2>
	   <h2>${deposito.tipo }</h2>
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