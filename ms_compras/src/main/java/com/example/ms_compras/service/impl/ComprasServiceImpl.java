package com.example.ms_compras.service.impl;

import com.example.ms_compras.entity.Compras;
import com.example.ms_compras.repository.ComprasRepository;
import com.example.ms_compras.service.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ComprasServiceImpl implements ComprasService {
    @Autowired
    private ComprasRepository categoriaRepository;
    @Override
    public List<Compras> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Compras> buscarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Compras guardar(Compras categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Compras actualizar(Compras categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void borrarPorId(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
