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

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>

    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Chequeras</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
    
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
	   
	   <button onclick="confirmarEliminarChequera('${chequera.idChequera}', ${chequera.idChequera})">Eliminar</button>
       
	</c:forEach>
	
	<form:form id="formEliminar" name="chequeras">
	   <input id="id_chequera" type="hidden" name="idChequera">
	</form:form>
	
</body>
</html>