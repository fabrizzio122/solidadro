package com.ms_cliente.ms_cliente.repository;

import com.ms_cliente.ms_cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
