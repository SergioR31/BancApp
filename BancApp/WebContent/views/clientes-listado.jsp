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
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>

	<c:forEach items="${clientes }" var="cliente">
	   <h1>${cliente.idCliente}</h1>
	   <h2>${cliente.nombre}</h2>
	   <h3>${cliente.apellidoPaterno}</h3>
	   <h3>${cliente.apellidoMaterno}</h3>
	   <h4>${cliente.direccion}</h4>
	   <h4>${cliente.estado}</h4>
	   <h4>${cliente.codigoPostal}</h4>
	   <h4>${cliente.telefono}</h4>
	   <h4>${cliente.correo}</h4>
	   <h4>${cliente.fechaNacimiento}</h4>
	   <h4>${cliente.rfc}</h4>
	   <h4>${cliente.status}</h4>
	   <form:form action="/BancApp/Clientes/modificar">
	       <button name="idCliente" value="${cliente.idCliente}">Modificar</button>
	   </form:form>
	   
           <button onclick="confirmarEliminarCliente('${cliente.nombre}', ${cliente.idCliente})">Eliminar</button>
       
	</c:forEach>
	
	<form:form id="formEliminar" name="clientes">
	   <input id="id_cliente" type="hidden" name="idCliente">
	</form:form>
	
</body>
</html>