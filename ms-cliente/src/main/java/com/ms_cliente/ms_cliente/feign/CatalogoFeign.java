package com.ms_cliente.ms_cliente.feign;

import com.ms_cliente.ms_cliente.dto.CategoriaDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "ms-catalogo", path = "/categoria")
public interface CatalogoFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "categoriaListarPorIdCB", fallbackMethod = "fallbackCategoria")
    public CategoriaDto buscarPorId(@PathVariable Integer id);

    default CategoriaDto fallbackCategoria(Integer id, Exception e) {
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId(9000000);
        categoriaDto.setNombre("SERVICIO NO DISPONIBLE");
        return categoriaDto;
    }
    @PostMapping
    @CircuitBreaker(name = "categoriaCrearCB", fallbackMethod = "fallbackCrearCategoria")
    public CategoriaDto crear(@RequestBody CategoriaDto categoria);

    default CategoriaDto fallbackCrearCategoria(CategoriaDto categoria, Exception e) {
        CategoriaDto dto = new CategoriaDto();
        dto.setId(9000001);
        dto.setNombre("NO SE PUDO CREAR (SERVICIO NO DISPONIBLE)");
        return dto;
    }
    @PutMapping("/{id}")
    @CircuitBreaker(name = "categoriaActualizarCB", fallbackMethod = "fallbackActualizarCategoria")
    public CategoriaDto actualizar(@PathVariable Integer id, @RequestBody CategoriaDto categoria);

    default CategoriaDto fallbackActualizarCategoria(Integer id, CategoriaDto categoria, Exception e) {
        CategoriaDto dto = new CategoriaDto();
        dto.setId(9000002);
        dto.setNombre("NO SE PUDO ACTUALIZAR (SERVICIO NO DISPONIBLE)");
        return dto;
    }
    @DeleteMapping("/{id}")
    @CircuitBreaker(name = "categoriaEliminarCB", fallbackMethod = "fallbackEliminarCategoria")
    public String eliminar(@PathVariable Integer id);

    default String fallbackEliminarCategoria(Integer id, Exception e) {
        return "No se pudo eliminar la categoría " + id + " (SERVICIO NO DISPONIBLE)";
    }



}
