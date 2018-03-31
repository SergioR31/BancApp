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
<title>Listado Bancos</title>
</head>
<body>
    
	<c:forEach items="${bancos }" var="banco">
	   <h1>${banco.idBanco}</h1>
	   <h2>${banco.entidad}</h2>
	   <h3>${banco.sucursal}</h3>
	   <h3>${banco.direccion}</h3>
	   <h4>${banco.status}</h4>
	   <form:form action="/BancApp/Bancos/modificar">
	       <button name="idBanco" value="${banco.idBanco}">Modificar</button>
	   </form:form>
	   
           <button onclick="confirmar('${banco.entidad}')">Eliminar</button>
       
	</c:forEach>
	
	<form:form id="formEliminar" name="bancos">
	   <input id="id_banco" type="hidden" name="idBanco">
	</form:form>
	
	<script>
	var mensaje = '${mensaje}'
	
	if (mensaje != null && mensaje != "") {
		alert('${mensaje}')
	}
	
	function confirmar(entidad){
		
		var input = document.getElementById('id_banco');
		input.value = idBanco;
		
		var formEliminar = document.getElementById('formEliminar');
        
		if (confirm("Desea elimiar el Banco " + entidad + "?")) {
			document.forms[formEliminar.id].action = "/BancApp/eliminarBanco";
			document.forms[formEliminar.id].submit();
		}
	}
	
	</script>
</body>
</html>