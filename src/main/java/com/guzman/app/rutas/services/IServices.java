package com.guzman.app.rutas.services;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IServices <T>{
    List<T> listar();
        Optional<T> getById(Long id);
        void guardaar(T t );
        void eliminar(Long id);
}
