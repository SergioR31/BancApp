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
<title>Modificar Cliente</title>
</head>
<body>
    <c:import url="/WEB-INF/fragments/boton-inicio.jsp"></c:import>
	<form:form action="/BancApp/modificarCliente">
	   <input type="hidden" name="idCliente" value="${cliente.idCliente }">	
		<input type="text" id="nombre" name="nombre" placeholder="Nombre" maxlength="20" value="${cliente.nombre}" required>
        <input type="text" id="apellido_paterno" name="apellidoPaterno" placeholder="Apellido Paterno" maxlength="20" value="${cliente.apellidoPaterno}" required>
        <input type="text" id="apellido_materno" name="apellidoMaterno" placeholder="Apellido Materno" maxlength="20" value="${cliente.apellidoMaterno}" required>
        <input type="text" id="direccion" name="direccion" placeholder="Direccion" maxlength="100" value="${cliente.direccion}" required>
        <input type="text" id="estado" name="estado" placeholder="Estado" maxlength="20" value="${cliente.estado}" required>
        <input type="number" id="codigo_postal" name="codigoPostal" placeholder="Codigo Postal" onKeyPress="if(this.value.length==5) return false;" oninput="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="5" value="${cliente.codigoPostal}" required>
        <input type="number" id="telefono" name="telefono" placeholder="Telefono" onKeyPress="if(this.value.length==10) return false;" oninput="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="10" value="${cliente.telefono}" required>
        <input type="text" id="correo" name="correo" placeholder="Correo" value="${cliente.correo}" maxlength="30" required>
        <input type="date" name="fechaNacimiento" step="1" min="1950-01-01" max="2000-12-31" placeholder="Fecha Nacimiento" value="${cliente.fechaNacimiento}" required>
        <input type="text" id="rfc" name="rfc" placeholder="RFC" maxlength="13" value="${cliente.rfc}" required>
		
		<button type="submit">Modificar Cliente</button>
	</form:form>
	
	<script>
	
	</script>
</body>
</html>