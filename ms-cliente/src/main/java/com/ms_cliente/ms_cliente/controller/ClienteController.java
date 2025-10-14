package com.ms_cliente.ms_cliente.controller;

import com.ms_cliente.ms_cliente.dto.ClienteDto;
import com.ms_cliente.ms_cliente.dto.ClienteRequestDto;
import com.ms_cliente.ms_cliente.entity.Cliente;
import com.ms_cliente.ms_cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteDto buscarPorId(@PathVariable Integer id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    public ClienteDto guardar(@RequestBody ClienteRequestDto request) {
        return clienteService.guardar(request);
    }



    @PutMapping("/{id}")
    public ClienteDto actualizar(@PathVariable Integer id, @RequestBody ClienteRequestDto request) {
        return clienteService.actualizar(id, request);
    }



    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        clienteService.borrarPorId(id);
        return "Categoria eliminada";
    }

    @DeleteMapping("/{id}/categoria")
    public String eliminarCategoriaDeCliente(@PathVariable Integer id) {
        return clienteService.eliminarCategoriaDeCliente(id);
    }

}
