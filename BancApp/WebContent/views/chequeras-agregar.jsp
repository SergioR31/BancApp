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
            
            <div class="row">
                <form:form action="/BancApp/insertarChequera">
                <div class="col-lg-6">
                    <div class="form-group">
                        <label>Fecha Apertura</label>
                        <input class="form-control" type="date" name="fechaApertura" step="1" min="2000-01-01" max="2018-04-10" placeholder="Fecha Apertura" required>
                    </div>
                    
                    <label>Saldo</label>
                    <div class="form-group input-group">
                        <span class="input-group-addon">$</span>
                        <input class="form-control" type="number" id="saldoApertura" name="saldoApertura" placeholder="Saldo Apertura" onKeyPress="if(this.value.length==10) return false;" oninput="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="10" required>
                        <span class="input-group-addon">.00</span>
                    </div>
                    
                </div>
                
                <div class="col-lg-6">
                
                    <div class="form-group">
                        <label>Cliente</label>
                        <select class="form-control" name="idCliente">
                            <c:forEach items="${clientes }" var="cliente">
                                <c:if test="${cliente.status eq 'Disponible' }">
                                    <option value="${cliente.idCliente }">${cliente.nombre } ${cliente.apellidoPaterno }</option>
                                </c:if>    
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label>Banco</label>
                        <select class="form-control" name="idBanco">
                            <c:forEach items="${bancos }" var="banco">
                                <c:if test="${banco.status eq 'Disponible' }">
                                    <option value="${banco.idBanco }">${banco.entidad }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    
                </div>
                
                <div class="col-lg-12">
                    <button class="btn btn-primary" type="submit">Agregar Chequera</button>
                </div>
                </form:form>
            </div>
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
	
</body>
</html>