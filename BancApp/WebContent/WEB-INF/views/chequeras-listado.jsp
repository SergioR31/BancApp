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
<title>Listado Chequeras</title>
</head>
<body>
    
	<c:forEach items="${chequeras }" var="chequera">
	   <h1>Chequera: ${chequera.idChequera }</h1>
	   <h2>Saldo de Apertura: ${chequera.saldoApertura }</h2>
	   <h2>Fecha de Apertura: ${chequera.fechaApertura }</h2>
	   <h2>Saldo Actual: ${chequera.saldo }</h2>
	   <h2>Status: ${chequera.status }</h2>
	   <h2>ID Banco: ${chequera.idBanco }</h2>
	   <h2>Entidad Bancaria: ${chequera.nombreBanco }</h2>
	   <h2>ID Cliente: ${chequera.idCliente }</h2>
       <h2>Titular de Chequera: ${chequera.nombreCliente }</h2>
       <h2>CLABE: ${chequera.clabe }</h2>
	   
	   <button onclick="confirmar('${chequera.idChequera}', ${chequera.idChequera})">Eliminar</button>
       
	</c:forEach>
	
	<form:form id="formEliminar" name="chequeras">
	   <input id="id_chequera" type="hidden" name="idChequera">
	</form:form>
	
	<script>
	var mensaje = '${mensaje}'
	
	if (mensaje != null && mensaje != "") {
		alert('${mensaje}')
	}
	
	function confirmar(nombre, idChequera){
		
		var input = document.getElementById('id_chequera');
		input.value = idChequera;
		
		var formEliminar = document.getElementById('formEliminar');
        
		if (confirm("Desea elimiar el Chequera " + nombre + "?")) {
			document.forms[formEliminar.id].action = "/BancApp/eliminarChequera";
			document.forms[formEliminar.id].submit();
		}
	}
	
	</script>
</body>
</html>