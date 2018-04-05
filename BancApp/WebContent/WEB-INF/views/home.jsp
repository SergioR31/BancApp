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
<title>HOME</title>
</head>
<body>
	<h1>BancApp</h1>
	
	<input type="button" onclick="location.href='/BancApp/Bancos/listado'" value="Listado Bancos">
	<input type="button" onclick="location.href='/BancApp/Bancos/agregar'" value="Agregar Banco">
	
	<input type="button" onclick="location.href='/BancApp/Clientes/listado'" value="Listado Clientes">
    <input type="button" onclick="location.href='/BancApp/Clientes/agregar'" value="Agregar Cliente">
	
	<input type="button" onclick="location.href='/BancApp/Chequeras/listado'" value="Listado Chequeras">
	<input type="button" onclick="location.href='/BancApp/Chequeras/agregar'" value="Agregar Chequera">
	
	<input type="button" onclick="location.href='/BancApp/Movimientos/retiro'" value="Hacer Retiro">
	<input type="button" onclick="location.href='/BancApp/Movimientos/deposito'" value="Hacer Deposito">
	<input type="button" onclick="location.href='/BancApp/Movimientos/transferencia'" value="Hacer Transferencia">
	
	<input type="button" onclick="location.href='/BancApp/ConsultaMovimientos'" value="Consultas">
	
	<input type="button" onclick="confirmar()" value="Borrar DB">
	
	<script>
    var mensaje = '${mensaje}'
    
    if (mensaje != null && mensaje != "") {
        alert('${mensaje}');
    }
    
    function confirmar(){
        
        if (confirm("Desea elimiar todos los datos de la base de datos? \n -Se borraran todos los Movimientos y Chequeras, tambien los catalogos de Bancos, Clientes.")) {
        	if (confirm("Estas seguro? \n -Todos los datos se perderan y no podran recuperarse.")){
        	    location.href='/BancApp/BorrarDB';
        	}
        }
    }
    
    </script>

</body>

</html>