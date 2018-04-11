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
<title>Estado de Cuenta</title>

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>

    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Estado de Cuenta</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
    
    <h1>${chequera.idChequera }</h1>
    <h2>${chequera.nombreBanco }</h2>
    <h2>${chequera.nombreCliente }</h2>
    <h2>${chequera.apellodoPCliente }</h2>
    <h2>Saldo Apertura: ${chequera.saldoApertura }</h2>
    <h2>Saldo Actual: ${chequera.saldo }</h2>
    <h2>Clabe: ${chequera.clabe }</h2>
    <h2>Consulta ${periodo }</h2>
    <c:if test="${anio != 0}">
        <h2>Año: ${anio }</h2>
    </c:if>
    <c:if test="${mes eq ''}">
        <h2>Mes: ${mes }</h2>
    </c:if>
    <h1>Depositos: ${totalDepositos }</h1>
    <h1>Retiros: ${totalRetiros }</h1>
    
	<c:forEach items="${movimientos }" var="movimiento">
	   <h1>${movimiento.idMovimiento }</h1>
	   <h2>${movimiento.concepto }</h2>
	   <h2>Monto: ${movimiento.monto }</h2>
	   <h2>${movimiento.fecha }</h2>
	   <h2>${movimiento.status }</h2>
	   <h2>${movimiento.operacion }</h2>
	   <h2>${movimiento.idTipo }</h2>
	   <h2>${movimiento.idChequera }</h2>
       <h2> Saldo: ${movimiento.saldo }</h2>
	   
	</c:forEach>
	
</body>
</html>