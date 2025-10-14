package com.ms_cliente.ms_cliente.service.Impl;

import com.ms_cliente.ms_cliente.dto.CategoriaDto;
import com.ms_cliente.ms_cliente.dto.ClienteDto;
import com.ms_cliente.ms_cliente.dto.ClienteRequestDto;
import com.ms_cliente.ms_cliente.entity.Cliente;
import com.ms_cliente.ms_cliente.feign.CatalogoFeign;
import com.ms_cliente.ms_cliente.repository.ClienteRepository;
import com.ms_cliente.ms_cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CatalogoFeign catalogoFeign;

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteDto buscarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id).get();
        CategoriaDto categoriaDto = catalogoFeign.buscarPorId(cliente.getIdcategoria());
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellido(cliente.getApellido());
        clienteDto.setCategoria(categoriaDto);
        return clienteDto;
    }

    @Override
    public ClienteDto guardar(ClienteRequestDto request) {
        // 1. Guardamos el cliente en la BD local sin idCategoria todavía
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        Cliente clienteGuardado = clienteRepository.save(cliente);

        // 2. Creamos la categoría en el ms-catalogo usando los datos del request
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNombre(request.getCategoria().getNombre());
        categoriaDto.setDescripcion(request.getCategoria().getDescripcion());

        CategoriaDto categoriaCreada = catalogoFeign.crear(categoriaDto);

        // 3. Actualizamos el cliente con el idCategoria que devolvió el catálogo
        clienteGuardado.setIdcategoria(categoriaCreada.getId());
        clienteRepository.save(clienteGuardado);

        // 4. Armamos la respuesta
        ClienteDto respuesta = new ClienteDto();
        respuesta.setId(clienteGuardado.getId());
        respuesta.setNombre(clienteGuardado.getNombre());
        respuesta.setApellido(clienteGuardado.getApellido());
        respuesta.setCategoria(categoriaCreada);

        return respuesta;
    }





    @Override
    public ClienteDto actualizar(Integer id, ClienteRequestDto request) {
        // 1. Verificamos si existe el cliente
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // 2. Actualizamos datos básicos del cliente
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());

        // 3. Actualizamos categoría en el ms-catalogo
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId(cliente.getIdcategoria()); // el id ya existe
        categoriaDto.setNombre(request.getCategoria().getNombre());
        categoriaDto.setDescripcion(request.getCategoria().getDescripcion());

        CategoriaDto categoriaActualizada = catalogoFeign.actualizar(cliente.getIdcategoria(), categoriaDto);

        // 4. Guardamos cambios en cliente
        Cliente clienteActualizado = clienteRepository.save(cliente);

        // 5. Armamos respuesta
        ClienteDto respuesta = new ClienteDto();
        respuesta.setId(clienteActualizado.getId());
        respuesta.setNombre(clienteActualizado.getNombre());
        respuesta.setApellido(clienteActualizado.getApellido());
        respuesta.setCategoria(categoriaActualizada);

        return respuesta;
    }



    @Override
    public void borrarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();

        // Primero eliminas en el catálogo (DELETE con CB)
        catalogoFeign.eliminar(cliente.getIdcategoria());

        // Luego eliminas en tu propia BD
        clienteRepository.deleteById(id);
    }

    @Override
    public String eliminarCategoriaDeCliente(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // 🔹 Aquí entra el CircuitBreaker definido en el Feign
        return catalogoFeign.eliminar(cliente.getIdcategoria());


    }
}