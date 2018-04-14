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
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Chequeras
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Fecha Apertura</th>
                                            <th>Saldo Apertura</th>
                                            <th>Saldo Actual</th>
                                            <th>CLABE</th>
                                            <th>Titular</th>
                                            <th>Banco</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${chequeras }" var="chequera">
                                            <c:if test="${chequera.status eq 'Activa' }">
                                            <tr>
                                                <td>${chequera.idChequera }</td>
                                                <td>${chequera.fechaApertura }</td>
                                                <td>$${chequera.saldoApertura }0</td>
                                                <td>$${chequera.saldo }0</td>
                                                <td>${chequera.clabe }</td>
                                                <td>${chequera.nombreCliente }</td>
                                                <td>${chequera.nombreBanco }</td>

                                                <td>
                                                    <button class="btn btn-danger" onclick="confirmarEliminarChequera('${chequera.idChequera}', ${chequera.idChequera})">Eliminar</button>
                                                </td>
                                            </tr>
                                            </c:if>
                                        </c:forEach>
                                        <form:form id="formEliminar" name="chequeras">
                                            <input id="id_chequera" type="hidden" name="idChequera">
                                        </form:form>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
	
</body>
</html>