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
<title>Modificar Banco</title>
</head>
<body>
	<form:form action="/BancApp/modificarBanco">
	   <input type="hidden" name="idBanco" value="${banco.idBanco }">
		<input type="text" id="nombre_entidad" name="nombre_entidad" placeholder="Entidad" value="${banco.entidad }" required>
		<input type="text" id="nombre_sucursal" name="nombre_sucursal" placeholder="Sucursal" value="${banco.sucursal }" required>
		<input type="text" id="direccion" name="direccion" placeholder="Direccion" value="${banco.direccion }" required>
		
		<button type="submit">Modificar Banco</button>
	</form:form>
	
	<script>
	var mensaje = '${mensaje}'
	
	if (mensaje != null && mensaje != "") {
		alert('${mensaje}')
	}
	
	</script>
</body>
</html>