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

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>
    
    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Clientes</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Clientes
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Direccion</th>
                                            <th>Estado</th>
                                            <th>C.P.</th>
                                            <th>Telefono</th>
                                            <th>Correo</th>
                                            <th>Fecha Nacimiento</th>
                                            <th>RFC</th>
                                            <th colspan="2">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${clientes }" var="cliente">
                                            <c:if test="${cliente.status eq 'Disponible' }">
                                            <tr>
                                                <td>${cliente.idCliente}</td>
                                                <td>${cliente.nombre} ${cliente.apellidoPaterno}</td>
                                                <td>${cliente.direccion}</td>
                                                <td>${cliente.estado}</td>
                                                <td>${cliente.codigoPostal}</td>
                                                <td>${cliente.telefono}</td>
                                                <td>${cliente.correo}</td>
                                                <td>${cliente.fechaNacimiento}</td>
                                                <td>${cliente.rfc}</td>
                                                <td>
                                                    <form:form action="/BancApp/Clientes/modificar">
                                                        <button class="btn btn-warning" name="idCliente" value="${cliente.idCliente}">Modificar</button>
                                                    </form:form>
                                                </td>
                                                <td>
                                                    <button class="btn btn-danger" onclick="confirmarEliminarCliente('${cliente.nombre}', ${cliente.idCliente})">Eliminar</button>
                                                </td>
                                            </tr>
                                            </c:if>
                                        </c:forEach>
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
    
    <form:form id="formEliminar" name="clientes">
       <input id="id_cliente" type="hidden" name="idCliente">
    </form:form>
    
    <c:import url="/fragments/scripts.jsp"></c:import>
	
</body>
</html>