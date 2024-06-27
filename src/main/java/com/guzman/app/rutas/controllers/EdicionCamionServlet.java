package com.guzman.app.rutas.controllers;

import com.guzman.app.rutas.models.Camion;
import com.guzman.app.rutas.models.enums.Marcas;
import com.guzman.app.rutas.models.enums.Tipos;
import com.guzman.app.rutas.services.CamionesService;
import com.guzman.app.rutas.services.IServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;

@WebServlet("/camiones/editar")
public class EdicionCamionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        // Lista de tipos y marcas
        Tipos[] arrayCamiones = Tipos.values();
        Marcas[] arrayMarcas = Marcas.values();
        req.setAttribute("tipos", arrayCamiones);
        req.setAttribute("marcas", arrayMarcas);

        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> listaAnios = new ArrayList<>();
        for (int i = anioActual - 20; i <= anioActual; i++) {
            listaAnios.add(i);
        }
        req.setAttribute("anios", listaAnios);

        IServices<Camion> service = new CamionesService(conn);
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Camion> optionalCamion = service.getById(id);

        if (optionalCamion == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Camión no encontrado");
            return;
        }
        Camion camion = optionalCamion.get();
        req.setAttribute("camion", camion);
        getServletContext().getRequestDispatcher("/edicionCamion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        IServices<Camion> service = new CamionesService(conn);
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Camion> optionalCamion = service.getById(id);

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

        if (matricula == null || matricula.isEmpty()) {
            errores.put("matricula", "La matrícula es obligatoria");
        }
        if (modelo == null || modelo <= 0) {
            errores.put("anio", "El año del modelo es obligatorio y debe ser positivo");
        }
        if (capacidad == null || capacidad <= 0) {
            errores.put("capacidad", "La capacidad es obligatoria y debe ser positiva");
        }
        if (kilometraje == null || kilometraje < 0) {
            errores.put("kilometraje", "El kilometraje es obligatorio y no puede ser negativo");
        }
        Camion camion = optionalCamion.get();
        camion.setMatricula(matricula);
        if (errores.isEmpty()) {
            camion.setTipoCamion(tipoCamion);
            camion.setModelo(modelo);
            camion.setMarca(marca);
            camion.setCapacidad(capacidad);
            camion.setKilometraje(kilometraje);
            camion.setDisponibilidad(disponibilidad);

            service.guardaar(camion);
            resp.sendRedirect(req.getContextPath() + "/camiones/listar");
        } else {

            // Lista de tipos y marcas
            Tipos[] arrayCamiones = Tipos.values();
            Marcas[] arrayMarcas = Marcas.values();
            req.setAttribute("tipos", arrayCamiones);
            req.setAttribute("marcas", arrayMarcas);

            int anioActual = Calendar.getInstance().get(Calendar.YEAR);
            List<Integer> listaAnios = new ArrayList<>();
            for (int i = anioActual - 20; i <= anioActual; i++) {
                listaAnios.add(i);
            }

            req.setAttribute("errores", errores);
            req.setAttribute("camion", camion);
            req.setAttribute("tipos", arrayCamiones);
            req.setAttribute("marcas", arrayMarcas);
            req.setAttribute("anios", listaAnios);
            getServletContext().getRequestDispatcher("/edicionCamion.jsp").forward(req, resp);
        }
    }
}

