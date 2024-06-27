package com.guzman.app.rutas.repositories;

import com.guzman.app.rutas.models.Ruta;

import java.sql.SQLException;

public interface IRustasRepository extends IRepository<Ruta>{

    Long guardarReturnId(Ruta ruta) throws SQLException;
}
