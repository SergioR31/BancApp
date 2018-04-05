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
<title>Consulta Movimientos</title>
</head>
<body>
    <c:import url="/WEB-INF/fragments/boton-inicio.jsp"></c:import>
	<h1>${mensaje}</h1>
	
    <h2>Chequera</h2>
	<select id="id_Chequera" onchange="setValorChequera()">
	   <option selected value="default">-- Selecciona una chequera --</option>
        <c:forEach items="${chequeras }" var="chequera">
            <option value="${chequera.idChequera }">${chequera.idChequera } - Saldo $${chequera.saldo } -</option>
        </c:forEach>
    </select>
        
    <form:form action="/BancApp/Consultar/estado-de-cuenta">
    
        <input id="id_chequeraEC" type="hidden" name="idChequera">
        
        <input type="number" id="anioEstadoCuenta" name="anioEstadoCuenta" placeholder="Año" onKeyPress="if(this.value.length==4) return false;" maxlength="4" required>
        
        <select name="mesEstadodeCuenta">
            <option value="01">Enero</option>
            <option value="02">Febrero</option>
            <option value="03">Marzo</option>
            <option value="04">Abril</option>
            <option value="05">Mayo</option>
            <option value="06">Junio</option>
            <option value="07">Julio</option>
            <option value="08">Agosto</option>
            <option value="09">Septiembre</option>
            <option value="10">Octubre</option>
            <option value="11">Noviembre</option>
            <option value="12">Diciembre</option>
        </select>
		
		<button type="submit">Estado de Cuenta</button>
		
	</form:form>
	
	<form:form id="formMovimientos">
	
	   <input id="id_chequeraMovimientos" type="hidden" name="idChequera">
	   
	   <select id="periodo" name="periodo" onchange="cambiarRequerido()">
	       <option value="completa">Completa</option>
	       <option value="anual">Anual</option>
	       <option value="mensual">Mensual</option>
	   </select>
	   
	   <input type="number" id="anioMovimientos" name="anioMovimientos" placeholder="Año" onKeyPress="if(this.value.length==4) return false;" maxlength="4">
	   
	   <select name="mesMovimientos">
            <option value="01">Enero</option>
            <option value="02">Febrero</option>
            <option value="03">Marzo</option>
            <option value="04">Abril</option>
            <option value="05">Mayo</option>
            <option value="06">Junio</option>
            <option value="07">Julio</option>
            <option value="08">Agosto</option>
            <option value="09">Septiembre</option>
            <option value="10">Octubre</option>
            <option value="11">Noviembre</option>
            <option value="12">Diciembre</option>
        </select>
        
        <button onclick="consultar('mDepositos')">Depositos</button>
        
        <button onclick="consultar('mRetiros')">Retiros</button>
        
        <button onclick="consultar('mTodo')">Todo</button>
	   
	   
	</form:form>
	
	<form:form id="formMovimientosF">
	
        <input id="id_chequeraMovimientosP" type="hidden" name="idChequera">
	
        <input type="date" name="desde" step="1" min="2000-01-01" max="2018-12-31" placeholder="Desde" required>
        
        <input type="date" name="hasta" step="1" min="2000-01-01" max="2018-12-31" placeholder="Desde">
	
        <button onclick="consultar('mDepositosF')">Depositos</button>
        
        <button onclick="consultar('mRetirosF')">Retiros</button>
        
        <button onclick="consultar('mTodoF')">Todo</button>
	   
	</form:form>
	
	<script>
	var mensaje = '${mensaje}'
	
	if (mensaje != null && mensaje != "") {
		alert('${mensaje}')
	}
	
	function cambiarRequerido(){
		var anioMovimientos = document.getElementById('anioMovimientos');
		var periodoselect = document.getElementById('periodo');
		
		if (periodoselect.value == 'completa'){
			anioMovimientos.required = false;
		} else{
			anioMovimientos.required = true;
		}
		
	}
	
	function setValorChequera(){
		
		var chequera = document.getElementById('id_Chequera');
		
		var chequeraEC = document.getElementById('id_chequeraEC');
	    var chequeraM = document.getElementById('id_chequeraMovimientos');
        var chequeraMP = document.getElementById('id_chequeraMovimientosP');
        
        chequeraEC.value = chequera.value;
        chequeraM.value = chequera.value;
        chequeraMP.value = chequera.value;
		
	}
	
	function consultar(ruta){
		
	    var chequera = document.getElementById('id_Chequera');
        
        var chequeraEC = document.getElementById('id_chequeraEC');
        var chequeraM = document.getElementById('id_chequeraMovimientos');
        var chequeraMP = document.getElementById('id_chequeraMovimientosP');
        
        chequeraEC.value = chequera.value;
        chequeraM.value = chequera.value;
        chequeraMP.value = chequera.value;
		
		var formMovimientos = document.getElementById('formMovimientos');
		var formMovimientosF = document.getElementById('formMovimientosF');
		
		var idchequera = document.getElementById('id_Chequera');
		
		if(idchequera.value == 'default' || idchequera.value == ''){
			alert('Seleccione una chequera');
		} else {
		
		if (ruta == 'mDepositos'){
			formMovimientos.action = "/BancApp/ConsultaMovimientos/depositos";
		}
		
	    if (ruta == 'mRetiros'){
            formMovimientos.action = "/BancApp/ConsultaMovimientos/retiros";
        }
	    
	    if (ruta == 'mTodo'){
            formMovimientos.action = "/BancApp/ConsultaMovimientos/todos";
        }
	    
	    
	    if (ruta == 'mDepositosF'){
	    	formMovimientosF.action = "/BancApp/ConsultaMovimientos/depositos-rango-fecha";
	    	console.log(formMovimientosF.action);
        }
        
        if (ruta == 'mRetirosF'){
        	formMovimientosF.action = "/BancApp/ConsultaMovimientos/retiros-rango-fecha";
        	console.log(formMovimientosF.action);
        }
        
        if (ruta == 'mTodoF'){
        	formMovimientosF.action = "/BancApp/ConsultaMovimientos/todos-rango-fecha";
        	console.log(formMovimientosF.action);
        }
        
		}
		
	}
	
	</script>
</body>
</html>