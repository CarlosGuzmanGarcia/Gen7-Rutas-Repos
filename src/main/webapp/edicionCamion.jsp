<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.guzman.app.rutas.models.*" %>
<%@ page import="com.guzman.app.rutas.models.enums.*" %>

<%
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Camion camion = (Camion) request.getAttribute("camion");
    Tipos[] tipos = (Tipos[]) request.getAttribute("tipos");
    Marcas[] marcas = (Marcas[]) request.getAttribute("marcas");
    List<Integer> anios = (List<Integer>) request.getAttribute("anios");
    Boolean estado = camion.getDisponibilidad();
    String disponible = estado ? "checked" : "";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Camión</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-inverse">
       <div class="container-fluid">
           <div class="navbar-header" id="div1">
               <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                   data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                   <span class="sr-only">Toggle navigation</span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="#" id="enlace1">Rutas App</a>
           </div>
           <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
               <ul class="nav navbar-nav">
                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Choferes<span
                               class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a href="<%=request.getContextPath()%>/choferes/listar">Lista Choferes</a></li>
                           <li><a href="<%=request.getContextPath()%>/choferes/alta">Alta Chofer</a></li>
                       </ul>
                   </li>
                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Camiones<span
                               class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a href="<%=request.getContextPath()%>/camiones/listar">Lista Camiones</a></li>
                           <li><a href="<%=request.getContextPath()%>/camiones/alta">Alta Camion</a></li>
                       </ul>
                   </li>
                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Rutas<span
                               class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a href="<%=request.getContextPath()%>/rutas/alta">Alta Ruta</a></li>
                       </ul>
                   </li>
               </ul>
           </div>
       </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-12">
                <h2>Formulario editar camión</h2>
            </div>
        </div>

        <br>

        <% if (errores != null && !errores.isEmpty()) { %>
            <ul class="alert alert-danger mx-5 px-5">
                <% for (String error : errores.values()) { %>
                    <li><%= error %></li>
                <% } %>
            </ul>
        <% } %>

        <div class="row">
            <form action="<%= request.getContextPath() %>/camiones/editar" method="post">
                <input type="hidden" name="id" value="<%=camion.getId()%>">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="matricula">Matrícula</label>
                        <input type="text" name="matricula" id="matricula" class="form-control" value="<%= camion.getMatricula() != null ? camion.getMatricula() : "" %>">
                        <% if (errores != null && errores.containsKey("matricula")) { %>
                            <span class='text-danger'><%= errores.get("matricula") %></span>
                        <% } %>
                    </div>

                    <div class="form-group">
                        <label for="tipo">Tipo</label>
                        <select name="tipo" id="tipo" class="form-control">
                            <% for (Tipos tipo : tipos) { %>
                                <option value="<%= tipo.name() %>" <%= camion.getTipoCamion() != null && camion.getTipoCamion().equals(tipo) ? "selected" : "" %>><%= tipo.name() %></option>
                            <% } %>
                        </select>
                        <% if (errores != null && errores.containsKey("tipo")) { %>
                            <span class='text-danger'><%= errores.get("tipo") %></span>
                        <% } %>
                    </div>

                    <div class="form-group">
                        <label for="anio">Modelo (Año)</label>
                        <select name="anio" id="anio" class="form-control">
                            <% for (Integer anio : anios) { %>
                                <option value="<%= anio %>" <%= camion.getModelo() != null && camion.getModelo().equals(anio) ? "selected" : "" %>><%= anio %></option>
                            <% } %>
                        </select>
                        <% if (errores != null && errores.containsKey("anio")) { %>
                            <span class='text-danger'><%= errores.get("anio") %></span>
                        <% } %>
                    </div>

                    <div class="form-group">
                        <label for="marca">Marca</label>
                        <select name="marca" id="marca" class="form-control">
                            <% for (Marcas marca : marcas) { %>
                                <option value="<%= marca.name() %>" <%= camion.getMarca() != null && camion.getMarca().equals(marca) ? "selected" : "" %>><%= marca.name() %></option>
                            <% } %>
                        </select>
                        <% if (errores != null && errores.containsKey("marca")) { %>
                            <span class='text-danger'><%= errores.get("marca") %></span>
                        <% } %>
                    </div>

                    <div class="form-group">
                        <label for="capacidad">Capacidad</label>
                        <input type="number" name="capacidad" id="capacidad" class="form-control" value="<%= camion.getCapacidad() != null ? camion.getCapacidad() : "" %>">
                        <% if (errores != null && errores.containsKey("capacidad")) { %>
                            <span class='text-danger'><%= errores.get("capacidad") %></span>
                        <% } %>
                    </div>

                    <div class="form-group">
                        <label for="kilometraje">Kilometraje</label>
                        <input type="number" step="0.1" name="kilometraje" id="kilometraje" class="form-control" value="<%= camion.getKilometraje() != null ? camion.getKilometraje() : "" %>">
                        <% if (errores != null && errores.containsKey("kilometraje")) { %>
                            <span class='text-danger'><%= errores.get("kilometraje") %></span>
                        <% } %>
                    </div>

                    <div class="form-group">
                        <label for="disponibilidad">Disponibilidad</label>
                        <input type="checkbox" name="disponibilidad" id="disponibilidad" class="form-control" <%= disponible %>>
                    </div>

                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
