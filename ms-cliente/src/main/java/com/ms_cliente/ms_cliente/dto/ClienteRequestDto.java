package com.ms_cliente.ms_cliente.dto;

import lombok.Data;

@Data
public class ClienteRequestDto {
    private String nombre;
    private String apellido;
    private CategoriaDto categoria;
}
