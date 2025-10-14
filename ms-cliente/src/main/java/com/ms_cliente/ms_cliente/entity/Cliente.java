package com.ms_cliente.ms_cliente.entity;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;;
import lombok.*;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "id", "nombre", "apellido" })
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String nombre;
    private String apellido;
    private Integer idcategoria;


}
