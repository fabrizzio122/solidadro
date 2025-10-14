package com.example.ms_compras.service;

import com.example.ms_compras.entity.Compras;

import java.util.List;
import java.util.Optional;

public interface ComprasService {
    List<Compras> listar();
    Optional<Compras> buscarPorId(Integer id);
    Compras guardar(Compras categoria);
    Compras actualizar(Compras categoria);
    void borrarPorId(Integer id);
}
