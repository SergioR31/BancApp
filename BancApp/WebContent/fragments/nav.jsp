<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
        <nav class="navbar navbar-default fixed-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/BancApp/Home">BancApp</a>
            </div>
            <!-- /.navbar-header -->
            
            <span id="fecha" class="nav navbar-top-links navbar-right dropdown-toggle" style="margin-right: 15px; margin-top: 8px;"></span>
            
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="/BancApp/Home"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bank fa-fw"></i> Bancos<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="/BancApp/Bancos/listado">Listado Bancos</a>
                                </li>
                                <li>
                                    <a href="/BancApp/Bancos/agregar">Agregar Banco</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-users fa-fw"></i> Clientes<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="/BancApp/Clientes/listado">Listado Clientes</a>
                                </li>
                                <li>
                                    <a href="/BancApp/Clientes/agregar">Agregar Cliente</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-money fa-fw"></i> Chequeras<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="/BancApp/Chequeras/listado">Listado Chequeras</a>
                                </li>
                                <li>
                                    <a href="/BancApp/Chequeras/agregar">Agregar Chequera</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-exchange fa-fw"></i> Movimientos<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="/BancApp/Movimientos/retiro">Retiro</a>
                                </li>
                                <li>
                                    <a href="/BancApp/Movimientos/deposito">Depsito</a>
                                </li>
                                <li>
                                    <a href="/BancApp/Movimientos/transferencia">Transferencia</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="/BancApp/ConsultaMovimientos"><i class="fa fa-list-alt fa-fw"></i> Consultas</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
            
        </nav>