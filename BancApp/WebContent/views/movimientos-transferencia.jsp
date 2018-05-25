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
            
            <div class="row">
                <form:form action="/BancApp/transferir">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Chequera de Retiro</label>
                            <select class="form-control" name="idChequera">
                                <c:forEach items="${chequeras }" var="chequera">
                                    <c:if test="${chequera.status eq 'Activa' }">
                                        <option value="${chequera.idChequera }">${chequera.idChequera } - Saldo $${chequera.saldo } - ${chequera.nombreCliente } ${chequera.apellodoPCliente }</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label>Fecha de Transferencia</label>
                            <input id="fechaMovimiento" class="form-control" type="datetime-local" name="fechaTransferencia" step="1" min="1900-01-01" max="2018-04-01" placeholder="Fecha Transferencia" required>
                        </div>
                    </div>
                    
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>CLABE</label>
                            <input class="form-control" type="number" id="clabe" name="clabe" placeholder="CLABE de Cuenta Receptora" onKeyPress="if(this.value.length==13) return false;" maxlength="13" required>
                        </div>
                        
                        <label>Monto</label>
                        <div class="form-group input-group">
                            <span class="input-group-addon">$</span>
                            <input class="form-control" type="number" id="monto" name="monto" placeholder="Monto a Transferir" onKeyPress="if(this.value.length==10) return false;" maxlength="10" required>
                            <span class="input-group-addon">.00</span>
                        </div>
                    </div>
                    
                    <div class="col-lg-12">
                        <div class="form-group">
                            <label>Concepto</label>
                            <input class="form-control" type="text" id="conepto" name="concepto" placeholder="Concepto de Transferencia" maxlength="100" required>
                        </div>
                        
                        <button class="btn btn-primary" type="submit">Transferir</button>
                    </div>
                </form:form>
            </div>
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
	
</body>
</html>