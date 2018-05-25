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
<title>Agregar Clientes</title>

<c:import url="/fragments/meta-links.jsp"></c:import>

</head>
<body>
    
    <div id="wrapper">
        
        <c:import url="/fragments/nav.jsp"></c:import>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Agregar Cliente</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
                <form:form action="/BancApp/insertarCliente">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Nombre</label>
                            <input class="form-control" type="text" id="nombre" name="nombre" placeholder="Nombre" maxlength="20" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Apellido Paterno</label>
                            <input class="form-control" type="text" id="apellido_paterno" name="apellidoPaterno" placeholder="Apellido Paterno" maxlength="20" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Apellido Materno</label>
                            <input class="form-control" type="text" id="apellido_materno" name="apellidoMaterno" placeholder="Apellido Materno" maxlength="20" required>
                        </div>
                    </div>
                    <!-- /.col-lg-6 -->
                    
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Direccion</label>
                            <input class="form-control" type="text" id="direccion" name="direccion" placeholder="Direccion" maxlength="100" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Estado</label>
                            <input class="form-control" type="text" id="estado" name="estado" placeholder="Estado" maxlength="20" required>
                        </div>
                        
                        <div class="form-group">
                            <label>C.P.</label>
                            <input class="form-control" type="number" id="codigo_postal" name="codigoPostal" placeholder="Codigo Postal" onKeyPress="if(this.value.length==5) return false;" oninput="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="5" required>
                        </div>
                    </div>
                    <!-- /.col-lg-6 -->
                    
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Telefono</label>
                            <input class="form-control" type="number" id="telefono" name="telefono" placeholder="Telefono" onKeyPress="if(this.value.length==10) return false;" oninput="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="10" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Correo</label>
                            <input class="form-control" type="email" id="correo" name="correo" placeholder="Correo" maxlength="30" required>
                        </div>
                    </div>
                    <!-- /.col-lg-6 -->
                    
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Fecha de Nacimiento</label>
                            <input class="form-control" type="date" name="fechaNacimiento" step="1" min="1950-01-01" max="2000-12-31" placeholder="Fecha Nacimiento" required>
                        </div>
                    </div>
                    <!-- /.col-lg-6 -->
                    
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>RFC</label>
                            <input class="form-control" type="text" id="rfc" name="rfc" placeholder="RFC" maxlength="13" required>
                        </div>
                    </div>
                    <!-- /.col-lg-6 -->
                    
                    <div class="col-lg-12">
                        <button class="btn btn-primary" type="submit">Agregar Cliente</button>
                    </div>
                    <!-- /.col-lg-12 -->
                </form:form>
            </div>
 
        </div>
    
    </div>
    <!-- /#wrapper -->
    
    <c:import url="/fragments/scripts.jsp"></c:import>
	
</body>
</html>