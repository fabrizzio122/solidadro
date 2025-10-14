package com.example.ms_compras.controller;


import com.example.ms_compras.entity.Compras;
import com.example.ms_compras.service.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    ComprasService comprasService;

    @GetMapping
    public List<Compras> listar() {
        return comprasService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Compras> buscarPorId(@PathVariable Integer id) {
        return comprasService.buscarPorId(id);
    }

    @PostMapping
    public Compras guardar(@RequestBody Compras compras) {
        return comprasService.guardar(compras);
    }

    @PutMapping
    public Compras actualizar(@RequestBody Compras compras) {
        return comprasService.actualizar(compras);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        comprasService.borrarPorId(id);
        return "Categoria eliminada";
    }
}
