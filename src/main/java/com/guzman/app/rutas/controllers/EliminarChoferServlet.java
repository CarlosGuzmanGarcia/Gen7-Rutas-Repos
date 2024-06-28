package com.guzman.app.rutas.controllers;

import com.guzman.app.rutas.models.Chofer;
import com.guzman.app.rutas.services.ChoferesService;
import com.guzman.app.rutas.services.IServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/choferes/eliminar")
public class EliminarChoferServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IServices<Chofer> service = new ChoferesService(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0l;
        }
        Chofer chofer;
        if(id > 0 ){
            Optional<Chofer> o = service.getById(id);
            if(o.isPresent()){
                try{
                    service.eliminar(id);
                    resp.sendRedirect(req.getContextPath()+ "/choferes/listar");
                }catch (Exception e){

                    resp.sendRedirect(req.getContextPath()+ "/choferes/listar?error=foreign_key_violation");

                }


            }
            else
            {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el chofer en la base de datos!");
            }
        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Errpr el id es null, se debe enviar como parametro en la url!");
        }
    }
}
