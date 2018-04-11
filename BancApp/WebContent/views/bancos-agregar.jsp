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
<title>Agregar Bancos</title>

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>

    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Agregar Banco</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
    
	<h1>${mensaje}</h1>
	<form:form action="/BancApp/insertarBanco">
		<input type="text" id="nombre_entidad" name="nombre_entidad" placeholder="Entidad" maxlength="20" required>
		<input type="text" id="nombre_sucursal" name="nombre_sucursal" placeholder="Sucursal" maxlength="20" required>
		<input type="text" id="direccion" name="direccion" placeholder="Direccion" maxlength="100" required>
		
		<button type="submit">Agregar Banco</button>
	</form:form>
	
</body>
</html>