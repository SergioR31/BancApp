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
</head>
<body>
	<h1>${mensaje}</h1>
	<form:form action="/BancApp/transferir">
	
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
	
	<script>
	var mensaje = '${mensaje}'
	
	if (mensaje != null && mensaje != "") {
		alert('${mensaje}')
	}
	
	</script>
</body>
</html>