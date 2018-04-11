<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <script>
    var mensaje = '${mensaje}'
    
    if (mensaje != null && mensaje != "") {
        alert('${mensaje}');
    }
    
    function confirmarBorrarDB(){
        
        if (confirm("Desea elimiar todos los datos de la base de datos? \n -Se borraran todos los Movimientos y Chequeras, tambien los catalogos de Bancos, Clientes.")) {
            if (confirm("Estas seguro? \n -Todos los datos se perderan y no podran recuperarse.")){
                location.href='/BancApp/BorrarDB';
            }
        }
    }
    
    function confirmarEliminarBanco(entidad, idBanco){
        
        var input = document.getElementById('id_banco');
        input.value = idBanco;
        
        var formEliminar = document.getElementById('formEliminar');
        
        if (confirm("Desea elimiar el Banco " + entidad + "?")) {
            document.forms[formEliminar.id].action = "/BancApp/eliminarBanco";
            document.forms[formEliminar.id].submit();
        }
    }
    
    function confirmarEliminarCliente(nombre, idCliente){
        
        var input = document.getElementById('id_cliente');
        input.value = idCliente;
        
        var formEliminar = document.getElementById('formEliminar');
        
        if (confirm("Desea elimiar el Cliente " + nombre + "?")) {
            document.forms[formEliminar.id].action = "/BancApp/eliminarCliente";
            document.forms[formEliminar.id].submit();
        }
    }
    
    function confirmarEliminarChequera(nombre, idChequera){
        
        var input = document.getElementById('id_chequera');
        input.value = idChequera;
        
        var formEliminar = document.getElementById('formEliminar');
        
        if (confirm("Desea elimiar el Chequera " + nombre + "?")) {
            document.forms[formEliminar.id].action = "/BancApp/eliminarChequera";
            document.forms[formEliminar.id].submit();
        }
    }
    
    function cambiarRequerido(){
        var anioMovimientos = document.getElementById('anioMovimientos');
        var periodoselect = document.getElementById('periodo');
        
        if (periodoselect.value == 'completa'){
            anioMovimientos.required = false;
        } else{
            anioMovimientos.required = true;
        }
        
    }
    
    function setValorChequera(){
        
        var chequera = document.getElementById('id_Chequera');
        
        var chequeraEC = document.getElementById('id_chequeraEC');
        var chequeraM = document.getElementById('id_chequeraMovimientos');
        var chequeraMP = document.getElementById('id_chequeraMovimientosP');
        
        chequeraEC.value = chequera.value;
        chequeraM.value = chequera.value;
        chequeraMP.value = chequera.value;
        
    }
    
    function consultar(ruta){
        
        var chequera = document.getElementById('id_Chequera');
        
        var chequeraEC = document.getElementById('id_chequeraEC');
        var chequeraM = document.getElementById('id_chequeraMovimientos');
        var chequeraMP = document.getElementById('id_chequeraMovimientosP');
        
        chequeraEC.value = chequera.value;
        chequeraM.value = chequera.value;
        chequeraMP.value = chequera.value;
        
        var formMovimientos = document.getElementById('formMovimientos');
        var formMovimientosF = document.getElementById('formMovimientosF');
        
        var idchequera = document.getElementById('id_Chequera');
        
        if(idchequera.value == 'default' || idchequera.value == ''){
            alert('Seleccione una chequera');
        } else {
        
        if (ruta == 'mDepositos'){
            formMovimientos.action = "/BancApp/ConsultaMovimientos/depositos";
        }
        
        if (ruta == 'mRetiros'){
            formMovimientos.action = "/BancApp/ConsultaMovimientos/retiros";
        }
        
        if (ruta == 'mTodo'){
            formMovimientos.action = "/BancApp/ConsultaMovimientos/todos";
        }
        
        
        if (ruta == 'mDepositosF'){
            formMovimientosF.action = "/BancApp/ConsultaMovimientos/depositos-rango-fecha";
            console.log(formMovimientosF.action);
        }
        
        if (ruta == 'mRetirosF'){
            formMovimientosF.action = "/BancApp/ConsultaMovimientos/retiros-rango-fecha";
            console.log(formMovimientosF.action);
        }
        
        if (ruta == 'mTodoF'){
            formMovimientosF.action = "/BancApp/ConsultaMovimientos/todos-rango-fecha";
            console.log(formMovimientosF.action);
        }
        
        }
        
    }
    
    </script>

    <!-- jQuery -->
    <script src="<c:url value="/assets/vendor/jquery/jquery.min.js" />"  ></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/assets/vendor/bootstrap/js/bootstrap.min.js" />"  ></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value="/assets/vendor/metisMenu/metisMenu.min.js" />"  ></script>

    <!-- Morris Charts JavaScript -->
    <script src="<c:url value="/assets/vendor/raphael/raphael.min.js" />"  ></script>
    <!-- <script src="<c:url value="/assets/vendor/morrisjs/morris.min.js" />"  ></script>  -->
    <!-- <script src="<c:url value="/assets/data/morris-data.js" />"  ></script>  -->

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/assets/dist/js/sb-admin-2.js" />"  ></script>