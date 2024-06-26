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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@WebServlet("/camiones/alta")
public class AltaCamionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Calculo de los años
        Calendar calendario = Calendar.getInstance();
        int anioActual = calendario.get(Calendar.YEAR);

        List<Integer> listaAnios = new ArrayList<>();
        for (int i = anioActual - 20; i <= anioActual + 1; i++) {
            listaAnios.add(i);
        }
        req.setAttribute("anios", listaAnios);
        //Envio de los enums
        Tipos[] arrayCamiones = Tipos.values();
        Marcas[] arrayMarcas = Marcas.values();
        req.setAttribute("tipos", arrayCamiones);
        req.setAttribute("marcas", arrayMarcas);
        getServletContext().getRequestDispatcher("/altaCamion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IServices<Camion> service = new CamionesService(conn);

        String matricula = req.getParameter("matricula");
        String tipoCamionStr = req.getParameter("tipo");
        Tipos tipoCamion = Tipos.valueOf(tipoCamionStr);
        Integer modelo = Integer.parseInt(req.getParameter("anio"));
        String marcaStr = req.getParameter("marca");
        Marcas marca = Marcas.valueOf(marcaStr);
        Integer capacidad = Integer.parseInt(req.getParameter("capacidad"));
        Double kilometraje = Double.parseDouble(req.getParameter("kilometraje"));

        String disponibilidadStr = req.getParameter("disponibilidad");
        Boolean disponibilidad = disponibilidadStr != null && disponibilidadStr.equalsIgnoreCase("true");

        Map<String, String> errores = new HashMap<>();

        if (errores.isEmpty()) {
            Camion camion = new Camion();
            camion.setId(0L);
            camion.setMatricula(matricula);
            camion.setTipoCamion(tipoCamion);
            camion.setModelo(modelo);
            camion.setMarca(marca);
            camion.setCapacidad(capacidad);
            camion.setKilometraje(kilometraje);
            camion.setDisponibilidad(disponibilidad);

            service.guardaar(camion);
            resp.sendRedirect(req.getContextPath() + "/camiones/listar");
        } else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaCamion.jsp").forward(req, resp);
        }
    }

}
