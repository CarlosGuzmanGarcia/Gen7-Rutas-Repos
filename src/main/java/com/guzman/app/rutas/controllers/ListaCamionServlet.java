package com.guzman.app.rutas.controllers;

import com.guzman.app.rutas.models.Camion;
import com.guzman.app.rutas.models.Chofer;
import com.guzman.app.rutas.models.enums.Marcas;
import com.guzman.app.rutas.models.enums.Tipos;
import com.guzman.app.rutas.services.CamionesService;
import com.guzman.app.rutas.services.ChoferesService;
import com.guzman.app.rutas.services.IServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet("/camiones/listar")
public class ListaCamionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");
        //Declaramos un objeto de tipo servicio

        IServices<Camion> service = new CamionesService(conn);
        List<Camion> camiones = service.listar();
        /*for (Chofer c: choferes){
            resp.getWriter().println("<h1>" + c.getId() + " -> "
        + c.getNombre()+ " -> " + c.getApPaterno() +" -> "+c.getApMaterno()+ "</h1>");
        }
        */
        req.setAttribute("camiones", camiones);
        getServletContext().getRequestDispatcher("/listaCamiones.jsp").forward(req, resp);
    }
}
