package com.ms_cliente.ms_cliente.dto;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Data
public class ClienteDto {

    private Integer Id;
    private String nombre;
    private String apellido;
    private Integer idcategoria;
    private CategoriaDto categoria;


}
