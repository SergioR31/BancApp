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
<title>Listado Movimientos</title>

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>

    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Movimientos por Fecha</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            Chequera
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="well well-sm">
                                        <h2>Chequera: ${chequera.idChequera }</h2>
                                        <h4>Clabe: ${chequera.clabe }</h4>
                                        <h4>Fecha Apertura: ${chequera.fechaApertura }</h4>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="well well-sm">
                                        
                                        <h4>Entidad Bancaria: ${chequera.nombreBanco }</h4>
                                        <h4>Cliente: ${chequera.nombreCliente } ${chequera.apellodoPCliente }</h4>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="well well-sm">
                                        <h4>Saldo Apertura: ${chequera.saldoApertura }</h4>
                                        <h4>Saldo Actual: ${chequera.saldo }</h4>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="well well-sm">
                                        <h3>Desde:  ${desde }</h3>
                                        <h3>Hasta: ${hasta }</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                </div>
                
                <div class="col-lg-4">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Resumen
                        </div>
                        <div class="panel-body">
                            <div id="donut-chart">
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            Movimientos
                        </div>
                        
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Concepto</th>
                                            <th>Monto</th>
                                            <th>Fecha</th>
                                            <th>Operacion</th>
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                        <c:forEach items="${movimientos }" var="movimiento">
                                            <c:if test="${movimiento.status eq 'Realizado' }">
                                                <tr>
                                                    <td>${movimiento.idMovimiento}</td>
                                                    <td>${movimiento.concepto }</td>
                                                    <td>$${movimiento.monto }0</td>
                                                    <td>${movimiento.fecha }</td>
                                                    <td>${movimiento.operacion }</td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
    
    <input type="hidden" id="totalDepositos" value="${totalDepositos }">
    <input type="hidden" id="totalRetiros" value="${totalRetiros }">
	
</body>
</html>