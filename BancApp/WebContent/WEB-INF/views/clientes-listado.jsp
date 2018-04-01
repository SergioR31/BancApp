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
<title>Listado Clientes</title>
</head>
<body>
    
	<c:forEach items="${clientes }" var="cliente">
	   <h1>${cliente.idCliente}</h1>
	   <h2>${cliente.nombre}</h2>
	   <h3>${cliente.apellidoPaterno}</h3>
	   <h3>${cliente.apellidoMaterno}</h3>
	   <h4>${cliente.direccion}</h4>
	   <h4>${cliente.estado}</h4>
	   <h4>${cliente.codigoPostal}</h4>
	   <h4>${cliente.telefono}</h4>
	   <h4>${cliente.correo}</h4>
	   <h4>${cliente.fechaNacimiento}</h4>
	   <h4>${cliente.rfc}</h4>
	   <h4>${cliente.status}</h4>
	   <form:form action="/BancApp/Clientes/modificar">
	       <button name="idBanco" value="${cliente.idCliente}">Modificar</button>
	   </form:form>
	   
           <button onclick="confirmar('${cliente.nombre}', ${cliente.idCliente})">Eliminar</button>
       
	</c:forEach>
	
	<form:form id="formEliminar" name="clientes">
	   <input id="id_cliente" type="hidden" name="idCliente">
	</form:form>
	
	<script>
	var mensaje = '${mensaje}'
	
	if (mensaje != null && mensaje != "") {
		alert('${mensaje}')
	}
	
	function confirmar(nombre, idCliente){
		
		var input = document.getElementById('id_cliente');
		input.value = idCliente;
		
		var formEliminar = document.getElementById('formEliminar');
        
		if (confirm("Desea elimiar el Cliente " + nombre + "?")) {
			document.forms[formEliminar.id].action = "/BancApp/eliminarCliente";
			document.forms[formEliminar.id].submit();
		}
	}
	
	</script>
</body>
</html>