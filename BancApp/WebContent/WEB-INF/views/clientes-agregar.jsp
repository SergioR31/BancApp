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
    <c:import url="/WEB-INF/fragments/boton-inicio.jsp"></c:import>
	<h1>${mensaje}</h1>
	<form:form action="/BancApp/insertarCliente">
		<input type="text" id="nombre" name="nombre" placeholder="Nombre" maxlength="20" required>
        <input type="text" id="apellido_paterno" name="apellidoPaterno" placeholder="Apellido Paterno" maxlength="20" required>
        <input type="text" id="apellido_materno" name="apellidoMaterno" placeholder="Apellido Materno" maxlength="20" required>
        <input type="text" id="direccion" name="direccion" placeholder="Direccion" maxlength="100" required>
        <input type="text" id="estado" name="estado" placeholder="Estado" maxlength="20" required>
        <input type="number" id="codigo_postal" name="codigoPostal" placeholder="Codigo Postal" onKeyPress="if(this.value.length==5) return false;" oninput="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="5" required>
        <input type="number" id="telefono" name="telefono" placeholder="Telefono" onKeyPress="if(this.value.length==10) return false;" oninput="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="10" required>
        <input type="text" id="correo" name="correo" placeholder="Correo" maxlength="30" required>
        <input type="date" name="fechaNacimiento" step="1" min="1950-01-01" max="2000-12-31" placeholder="Fecha Nacimiento" required>
        <input type="text" id="rfc" name="rfc" placeholder="RFC" maxlength="13" required>
		
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