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
<title>Agregar Clientes</title>
</head>
<body>
	<h1>${mensaje}</h1>
	<form:form action="/BancApp/insertarCliente">
		<input type="text" id="nombre" name="nombre" placeholder="Nombre" max="20" required>
		<input type="text" id="apellido_paterno" name="apellidoPaterno" placeholder="Apellido Paterno" max="20" required>
		<input type="text" id="apellido_materno" name="apellidoMaterno" placeholder="Apellido Materno" max="20" required>
		<input type="text" id="direccion" name="direccion" placeholder="Direccion" max="100" required>
		<input type="text" id="estado" name="estado" placeholder="Estado" max="20" required>
		<input type="number" id="codigo_postal" name="codigoPostal" placeholder="Codigo Postal" max="5" required>
		<input type="number" id="telefono" name="telefono" placeholder="Telefono" max="10" required>
		<input type="text" id="correo" name="correo" placeholder="Correo" required>
		<input type="date" name="fechaNacimiento" step="1" min="1950-01-01" max="2000-12-31" placeholder="Fecha Nacimiento">
		<input type="text" id="rfc" name="rfc" placeholder="RFC" max="13" required>
		
		<button type="submit">Agregar Cliente</button>
	</form:form>
	
	<script>
	var mensaje = '${mensaje}'
	
	if (mensaje != null && mensaje != "") {
		alert('${mensaje}')
	}
	
	</script>
</body>
</html>