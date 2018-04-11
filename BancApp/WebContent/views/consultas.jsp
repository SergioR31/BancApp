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
<title>Consultas</title>

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>
    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Consultas</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
	
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
		
		<button type="submit" onclick="setValorChequera()">Estado de Cuenta</button>
		
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
        
        <input type="datetime-local" name="desde" step="1" placeholder="Desde Fecha" value="2000-01-01T00:00" required>
        
        <input type="datetime-local" name="hasta" step="1" placeholder="Hasta Fecha" value="2018-04-01T23:59:59" required>
	
        
        
        <button onclick="consultar('mDepositosF')">Depositos</button>
        
        <button onclick="consultar('mRetirosF')">Retiros</button>
        
        <button onclick="consultar('mTodoF')">Todo</button>
	   
	</form:form>
	
</body>
</html>