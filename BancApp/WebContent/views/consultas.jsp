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
<title>Consultas</title>

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>
    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Consulta de Movimientos</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    Chequera
                                </div>
                                <div class="panel-body">
                                    <select class="form-control" id="id_Chequera" onchange="setValorChequera()">
                                        <option selected value="default">-- Selecciona una chequera --</option>
                                        <c:forEach items="${chequeras }" var="chequera">
                                            <option value="${chequera.idChequera }">${chequera.idChequera } - Saldo $${chequera.saldo } -</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <!-- /.panel-info -->
                            
                            <div class="panel-group" id="accordion">
<%--                                 <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" class="collapsed">Estado de Cuenta</a>
                                        </h4>
                                    </div>
                                    <div id="collapseOne" class="panel-collapse collapse" aria-expanded="true" style="">
                                        <div class="panel-body">
                                            <form:form id="formEstadoCuenta" onsubmit="event.preventDefault(); validarForm();">
                                            
                                                <input id="id_chequeraEC" type="hidden" name="idChequera">
                                                
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label>Año</label>
                                                        <input class="form-control" type="number" id="anioEstadoCuenta" name="anioEstadoCuenta" placeholder="Año" onKeyPress="if(this.value.length==4) return false;" maxlength="4" required>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label>Mes</label>
                                                        <select class="form-control" name="mesEstadodeCuenta">
                                                            <option value="01">Enero</option>
                                                            <option value="02">Febrero</option>
                                                            <option value="03">Marzo</option>
                                                            <option value="04">Abril</option>
                                                            <option value="05">Mayo</option>
                                                            <option value="06">Junio</option>
                                                            <option value="07">Julio</option>
                                                            <option value="08">Agosto</option>
                                                            <option value="09">Septiembre</option>
                                                            <option value="10">Octubre</option>
                                                            <option value="11">Noviembre</option>
                                                            <option value="12">Diciembre</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-lg-12">
                                                    <button class="btn btn-primary" onclick="consultar('estado-cuenta')">Estado de Cuenta</button>
                                                </div>
                                                
                                                
                                            </form:form>
                                        </div>
                                    </div>
                                </div> --%>
                                <div class="panel panel-default">
                                    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" class="collapsed">
                                        <h4 class="panel-title">
                                            Por periodo
                                        </h4>
                                    </div>
                                    <div id="collapseTwo" class="panel-collapse collapse" aria-expanded="true" style="">
                                        <div class="panel-body">
                                            <form:form id="formMovimientos" onsubmit="event.preventDefault(); validarForm();">
                                                <input id="id_chequeraMovimientos" type="hidden" name="idChequera">
                                                
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label>Tipo de Consulta</label>
                                                        <select class="form-control" id="periodo" name="periodo" onchange="cambiarRequerido()">
                                                            <option value="completa">Completa</option>
                                                            <option value="anual">Anual</option>
                                                            <option value="mensual">Mensual</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label>Año</label>
                                                        <input class="form-control" type="number" id="anioMovimientos" name="anioMovimientos" placeholder="Año" onKeyPress="if(this.value.length==4) return false;" maxlength="4">
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <label>Mes</label>
                                                        <select class="form-control" name="mesMovimientos">
                                                            <option value="01">Enero</option>
                                                            <option value="02">Febrero</option>
                                                            <option value="03">Marzo</option>
                                                            <option value="04">Abril</option>
                                                            <option value="05">Mayo</option>
                                                            <option value="06">Junio</option>
                                                            <option value="07">Julio</option>
                                                            <option value="08">Agosto</option>
                                                            <option value="09">Septiembre</option>
                                                            <option value="10">Octubre</option>
                                                            <option value="11">Noviembre</option>
                                                            <option value="12">Diciembre</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                

                                                    <div class="col-lg-4">
                                                        <button class="btn btn-primary btn-block" onclick="consultar('mDepositos')">Depositos</button>
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <button class="btn btn-info btn-block" onclick="consultar('mRetiros')">Retiros</button>
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <button class="btn btn-success btn-block" onclick="consultar('mTodo')">Todo</button>
                                                    </div>

                                                
                                            </form:form>
                                        </div>
                                    </div>
                                    
                                    
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" class="collapsed">
                                        <h4 class="panel-title">
                                            Por fechas
                                        </h4>
                                    </div>
                                    
                                    <div id="collapseThree" class="panel-collapse collapse" aria-expanded="true" style="">
                                        <div class="panel-body">
                                            <form:form id="formMovimientosF" onsubmit="event.preventDefault(); validarForm();">
                                                <input id="id_chequeraMovimientosP" type="hidden" name="idChequera">
                                                
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label>Desde</label>
                                                        <input class="form-control" type="datetime-local" name="desde" step="1" placeholder="Desde Fecha" value="2000-01-01T00:00" required>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label>Hasta</label>
                                                        <input id="hasta" class="form-control" type="datetime-local" name="hasta" step="1" placeholder="Hasta Fecha" value="2018-04-01T23:59:59" required>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-lg-4">
                                                    <button class="btn btn-primary btn-block" onclick="consultar('mDepositosF')">Depositos</button>
                                                </div>
                                                
                                                <div class="col-lg-4">
                                                    <button class="btn btn-info btn-block" onclick="consultar('mRetirosF')">Retiros</button>
                                                </div>
                                                
                                                <div class="col-lg-4">
                                                    <button class="btn btn-success btn-block" onclick="consultar('mTodoF')">Todo</button>
                                                </div>
                                                
                                            </form:form>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
	
<%--     <h2>Chequera</h2>
	<select id="id_Chequera" onchange="setValorChequera()">
	   <option selected value="default">-- Selecciona una chequera --</option>
        <c:forEach items="${chequeras }" var="chequera">
            <option value="${chequera.idChequera }">${chequera.idChequera } - Saldo $${chequera.saldo } -</option>
        </c:forEach>
    </select> --%>
        
<%--     <form:form action="/BancApp/Consultar/estado-de-cuenta">
    
        <input id="id_chequeraEC" type="hidden" name="idChequera">
        
        <input type="number" id="anioEstadoCuenta" name="anioEstadoCuenta" placeholder="Año" onKeyPress="if(this.value.length==4) return false;" maxlength="4" required>
        
        <select name="mesEstadodeCuenta">
            <option value="01">Enero</option>
            <option value="02">Febrero</option>
            <option value="03">Marzo</option>
            <option value="04">Abril</option>
            <option value="05">Mayo</option>
            <option value="06">Junio</option>
            <option value="07">Julio</option>
            <option value="08">Agosto</option>
            <option value="09">Septiembre</option>
            <option value="10">Octubre</option>
            <option value="11">Noviembre</option>
            <option value="12">Diciembre</option>
        </select>
		
		<button type="submit" onclick="setValorChequera()">Estado de Cuenta</button>
		
	</form:form> --%>
	
	<%-- <form:form id="formMovimientos">
	
	   <input id="id_chequeraMovimientos" type="hidden" name="idChequera">
	   
	   <select id="periodo" name="periodo" onchange="cambiarRequerido()">
	       <option value="completa">Completa</option>
	       <option value="anual">Anual</option>
	       <option value="mensual">Mensual</option>
	   </select>
	   
	   <input type="number" id="anioMovimientos" name="anioMovimientos" placeholder="Año" onKeyPress="if(this.value.length==4) return false;" maxlength="4">
	   
	   <select name="mesMovimientos">
            <option value="01">Enero</option>
            <option value="02">Febrero</option>
            <option value="03">Marzo</option>
            <option value="04">Abril</option>
            <option value="05">Mayo</option>
            <option value="06">Junio</option>
            <option value="07">Julio</option>
            <option value="08">Agosto</option>
            <option value="09">Septiembre</option>
            <option value="10">Octubre</option>
            <option value="11">Noviembre</option>
            <option value="12">Diciembre</option>
        </select>
        
        <button onclick="consultar('mDepositos')">Depositos</button>
        
        <button onclick="consultar('mRetiros')">Retiros</button>
        
        <button onclick="consultar('mTodo')">Todo</button>
	   
	   
	</form:form> --%>
	
	<%-- <form:form id="formMovimientosF">
	
        <input id="id_chequeraMovimientosP" type="hidden" name="idChequera">
        
        <input type="datetime-local" name="desde" step="1" placeholder="Desde Fecha" value="2000-01-01T00:00" required>
        
        <input type="datetime-local" name="hasta" step="1" placeholder="Hasta Fecha" value="2018-04-01T23:59:59" required>
	
        
        
        <button onclick="consultar('mDepositosF')">Depositos</button>
        
        <button onclick="consultar('mRetirosF')">Retiros</button>
        
        <button onclick="consultar('mTodoF')">Todo</button>
	   
	</form:form> --%>
	
</body>
</html>