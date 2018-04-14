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

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>
    
    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Bancos</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Bancos
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Entidad</th>
                                            <th>Sucursal</th>
                                            <th>Direccion</th>
                                            <th colspan="2">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${bancos }" var="banco">
                                            <c:if test="${banco.status eq 'Disponible' }">
                                            <tr>
                                                <td>${banco.idBanco}</td>
                                                <td>${banco.entidad}</td>
                                                <td>${banco.sucursal}</td>
                                                <td>${banco.direccion}</td>
                                                <td>
                                                    <form:form action="/BancApp/Bancos/modificar">
                                                        <button class="btn btn-warning" name="idBanco" value="${banco.idBanco}">Modificar</button>
                                                    </form:form>
                                                </td>
                                                <td>
                                                    <button class="btn btn-danger" onclick="confirmarEliminarBanco('${banco.entidad}' ,${banco.idBanco})">Eliminar</button>
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
	
	<form:form id="formEliminar" name="bancos">
	   <input id="id_banco" type="hidden" name="idBanco">
	</form:form>
	
	<c:import url="/fragments/scripts.jsp"></c:import>
	
</body>
</html>