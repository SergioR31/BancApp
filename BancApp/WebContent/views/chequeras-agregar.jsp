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
<title>Agregar Chequera</title>

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>
    
    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Agregar Chequera</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
    
	<form:form action="/BancApp/insertarChequera">
	
	   <input type="date" name="fechaApertura" step="1" min="2000-01-01" max="2018-04-01" placeholder="Fecha Apertura" required>
	   
		<input type="number" id="saldoApertura" name="saldoApertura" placeholder="Saldo Apertura" onKeyPress="if(this.value.length==10) return false;" oninput="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="10" required>
		
		<select name="idBanco">
		  <c:forEach items="${bancos }" var="banco">
		      <option value="${banco.idBanco }">${banco.entidad }</option>
		  </c:forEach>
		</select>
		
		<select name="idCliente">
          <c:forEach items="${clientes }" var="cliente">
          <option value="${cliente.idCliente }">${cliente.nombre }</option>
          </c:forEach>
        </select>
		
		
		<button type="submit">Agregar Chequera</button>
	</form:form>
	
</body>
</html>