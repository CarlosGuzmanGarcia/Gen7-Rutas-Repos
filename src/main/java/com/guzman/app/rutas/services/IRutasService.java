package com.guzman.app.rutas.services;

import com.guzman.app.rutas.models.Camion;
import com.guzman.app.rutas.models.Chofer;
import com.guzman.app.rutas.models.Ruta;

import java.util.List;

public interface IRutasService extends IServices<Ruta> {

    List<Camion> listarCamiones();

    List<Chofer> listarChoferes();

    Long guardarReturnId(Ruta ruta);
}
