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
<title>Modificar Banco</title>

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>
    
    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Modificar Banco</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
    
	<form:form action="/BancApp/modificarBanco">
	   <input type="hidden" name="idBanco" value="${banco.idBanco }">
		<input type="text" id="nombre_entidad" name="nombre_entidad" placeholder="Entidad" value="${banco.entidad }" maxlength="20" required>
		<input type="text" id="nombre_sucursal" name="nombre_sucursal" placeholder="Sucursal" value="${banco.sucursal }" maxlength="20" required>
		<input type="text" id="direccion" name="direccion" placeholder="Direccion" value="${banco.direccion }" maxlength="100" required>
		
		<button type="submit">Modificar Banco</button>
	</form:form>
	
	<script>
	
	</script>
</body>
</html>