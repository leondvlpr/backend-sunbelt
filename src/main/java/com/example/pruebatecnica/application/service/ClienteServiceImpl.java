package com.example.pruebatecnica.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pruebatecnica.application.dto.ClienteDTO;
import com.example.pruebatecnica.domain.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Override
    public Cliente consultarCliente(ClienteDTO clienteInfo) {

        Cliente cliente = new Cliente();

        if (clienteInfo.getTipoDocumento() == null || clienteInfo.getTipoDocumento().isEmpty()) {
            throw new IllegalArgumentException("El campo tipoDocumento es obligatorio");
        }

        if (clienteInfo.getNumeroDocumento() == null || clienteInfo.getNumeroDocumento().isEmpty()) {
            throw new IllegalArgumentException("El campo numeroDocumento es obligatorio");
        }

        if (!"C".equals(clienteInfo.getTipoDocumento()) && !"P".equals(clienteInfo.getTipoDocumento())) {
            throw new IllegalArgumentException("El tipo de documento debe ser C - Cédula de ciudadanía o P - Pasaporte");
        }

        if (!"10121314".equals(clienteInfo.getNumeroDocumento())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        cliente.setPrimerNombre(cliente.getPrimerNombre());
        cliente.setSegundoNombre(cliente.getSegundoNombre());
        cliente.setPrimerApellido(cliente.getPrimerApellido());
        cliente.setSegundoApellido(cliente.getSegundoApellido());
        cliente.setTelefono(cliente.getTelefono());
        cliente.setDireccion(cliente.getDireccion());
        cliente.setCiudadResidencia(cliente.getCiudadResidencia());

        return cliente;
    }

}
