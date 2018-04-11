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
<title>Transferencia</title>

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>
    
    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Transferencia</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
    
	<form:form action="/BancApp/transferir">
	   
	   <input type="datetime-local" name="fechaTransferencia" step="1" min="1900-01-01" max="2018-04-01" placeholder="Fecha Transferencia" required>
	
	   <select name="idChequera">
          <c:forEach items="${chequeras }" var="chequera">
              <option value="${chequera.idChequera }">${chequera.idChequera } - Saldo $${chequera.saldo } -</option>
          </c:forEach>
        </select>
        
		<input type="number" id="clabe" name="clabe" placeholder="CLABE" onKeyPress="if(this.value.length==13) return false;" maxlength="13" required>
		
		<input type="number" id="monto" name="monto" placeholder="Monto" onKeyPress="if(this.value.length==10) return false;" maxlength="10" required>
		
		<input type="text" id="conepto" name="concepto" placeholder="Concepto" maxlength="100">
		
		<button type="submit">Transferir</button>
		
	</form:form>
	
</body>
</html>