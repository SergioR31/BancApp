<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- jQuery -->
    <script src="<c:url value="/assets/vendor/jquery/jquery.min.js" />"  ></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/assets/vendor/bootstrap/js/bootstrap.min.js" />"  ></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value="/assets/vendor/metisMenu/metisMenu.min.js" />"  ></script>

    <!-- Morris Charts JavaScript -->
    <script src="<c:url value="/assets/vendor/raphael/raphael.min.js" />"  ></script>
    <script src="<c:url value="/assets/vendor/morrisjs/morris.min.js" />"  ></script>
    <%-- <script src="<c:url value="/assets/data/morris-data.js" />"  ></script> --%>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/assets/dist/js/sb-admin-2.js" />"  ></script>
    
    <script>
    var mensaje = '${mensaje}';
    
    if (mensaje != null && mensaje != "") {
        alert('${mensaje}');
    }
    
    var today = new Date();
    var y = today.getFullYear();
    var m = today.getMonth() + 1 ;
    var d = today.getDate();
    var h = today.getHours();
    var mm = today.getMinutes();
    var s = today.getSeconds();
    
    if (h < 10){
    	h = "0"+h;
    }
    
    if (m < 10){
    	m = "0"+m;
    }
    
    if (mm < 10){
        mm = "0"+mm;
    }
    
    if(d < 10){
    	d = "0"+d;
    }
    
    if(s < 10 ){
    	s = "0"+s;
    }
    
    if (document.getElementById('hasta') != null){
    	document.getElementById('hasta').value = y+"-"+m+"-"+d+"T"+h+":"+mm+":"+s ;
    }
    
    if (document.getElementById('fechaMovimiento') != null) {
    	document.getElementById('fechaMovimiento').value = y+"-"+m+"-"+d+"T"+h+":"+mm+":"+s ;
    }
    
    var style = "text-decoration:none;";
    var fecha = d +"/" + m + "/" + y ;
    
    document.getElementById('fecha').innerHTML = "<a style="+style+"><h4>Hoy: " + fecha + "</h4></a>";
    
    //console.log(y+"-"+m+"-"+d+"T"+h+":"+mm+":"+s);
    
    jQuery(document).ready(function(){
    	
    	var info = "Sin datos";
    	var depositos = "Depositos";
    	var label;
    	
    	if ($("#totalDepositos").val() == 0){
    		label = info
    	} else {
    		label = depositos;
    	}
    	
    	if ($("#donut-chart").html() != undefined){
    		Morris.Donut({
                element: 'donut-chart',
                data: [{
                    label: label,
                    value: $("#totalDepositos").val()
                }, {
                    label: "Retiros",
                    value: $("#totalRetiros").val()
                }],
                resize: true
            });
    	}
    	
    })
    
    function confirmarBorrarDB(){
        
        var password = document.getElementById('password');
        
        var form = document.getElementById('borrarDBForm');
        
        if (confirm("Desea elimiar todos los datos de la base de datos? \n -Se borraran todos los Movimientos y Chequeras, tambien los catalogos de Bancos, Clientes.")) {
            if (confirm("Estas seguro? \n -Todos los datos se perderan y no podran recuperarse.")){
            	
            	var pass = prompt('Ingresa contraseña');
            	
            	password.value = pass;
            	
            	console.log("form action: " + form.action);
            	
            	if (pass == '' || pass == null){
            		
            		console.log("pass empty ");
            		
                    return false;
                    
            	} else {
            		
            		console.log("pass not empty " + password.value);
            		
            		console.log("form action: " + form.action);
            		
            		form.action="/BancApp/BorrarDB";
            		
            		form.submit();
            		
            		console.log("form action: " + form.action);

            		return true;
            	}
            } else {
            	return false;
            }
        } else {
        	return false;
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
        }
        
        if (periodoselect.value == 'anual'){
            anioMovimientos.required = true;
        }
        
        if (periodoselect.value == 'mensual'){
        	anioMovimientos.required = true;
        } 
    }
    
    function setValorChequera(){
        
        var idchequera = document.getElementById('id_Chequera');
        
        var chequeraEC = document.getElementById('id_chequeraEC');
        var chequeraM = document.getElementById('id_chequeraMovimientos');
        var chequeraMP = document.getElementById('id_chequeraMovimientosP');
        
        if(idchequera.value == 'default' || idchequera.value == ''){
            alert('Seleccione una chequera');
            idchequera.focus();
        }else{
        
        chequeraEC.value = idchequera.value;
        chequeraM.value = idchequera.value;
        chequeraMP.value = idchequera.value;
        
        }
        
    }
    
    function consultar(ruta){
    	
    	var anioEstadoCuenta = document.getElementById('anioEstadoCuenta');
            	
    	var anioMovimientos = document.getElementById('anioMovimientos');
        var periodoselect = document.getElementById('periodo');
        
        var chequera = document.getElementById('id_Chequera');
        
        var chequeraEC = document.getElementById('id_chequeraEC');
        var chequeraM = document.getElementById('id_chequeraMovimientos');
        var chequeraMP = document.getElementById('id_chequeraMovimientosP');
        
        chequeraEC.value = chequera.value;
        chequeraM.value = chequera.value;
        chequeraMP.value = chequera.value;
        
        var formEstadoCuenta = document.getElementById('formEstadoCuenta');
        var formMovimientos = document.getElementById('formMovimientos');
        var formMovimientosF = document.getElementById('formMovimientosF');
        
        var idchequera = document.getElementById('id_Chequera');
        
        if(idchequera.value == 'default' || idchequera.value == ''){
            chequera.focus();
        } else if(anioMovimientos.required == true && (anioMovimientos.value == 0 || anioMovimientos.value == '')) {
        	alert('Ingrese año');
        	anioMovimientos.focus();
        } else {
        
        if (ruta == 'mDepositos'){
            formMovimientos.action = "/BancApp/ConsultaMovimientos/depositos";
            formMovimientos.submit();
        }
        
        if (ruta == 'mRetiros'){
            formMovimientos.action = "/BancApp/ConsultaMovimientos/retiros";
            formMovimientos.submit();
        }
        
        if (ruta == 'mTodo'){
            formMovimientos.action = "/BancApp/ConsultaMovimientos/todos";
            formMovimientos.submit();
        }
        
        
        if (ruta == 'mDepositosF'){
            formMovimientosF.action = "/BancApp/ConsultaMovimientos/depositos-rango-fecha";
            formMovimientosF.submit();
        }
        
        if (ruta == 'mRetirosF'){
            formMovimientosF.action = "/BancApp/ConsultaMovimientos/retiros-rango-fecha";
            formMovimientosF.submit();
        }
        
        if (ruta == 'mTodoF'){
            formMovimientosF.action = "/BancApp/ConsultaMovimientos/todos-rango-fecha";
            formMovimientosF.submit();
        }
        
        if (ruta == 'estado-cuenta'){
        	if(anioEstadoCuenta.value == 0 || anioEstadoCuenta.value == '') {
                alert('Ingrese año');
                anioEstadoCuenta.focus();
            } else {
            	formEstadoCuenta.action= "/BancApp/estado-de-cuenta";
                formEstadoCuenta.submit();
            }
        }
        
        }
        
    }
    
    function validarForm() {
    	
        var idchequera = document.getElementById('id_Chequera');
        
        var chequeraEC = document.getElementById('id_chequeraEC');
        var chequeraM = document.getElementById('id_chequeraMovimientos');
        var chequeraMP = document.getElementById('id_chequeraMovimientosP');       
        
        if(idchequera.value == 'default' || idchequera.value == ''){
            alert('Seleccione una chequera');
            idchequera.focus();
            return false;
            
        } else {
        	
        chequeraEC.value = idchequera.value;
        chequeraM.value = idchequera.value;
        chequeraMP.value = idchequera.value;
        return true;
        
        }
    }   
    
    </script>
    