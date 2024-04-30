package com.example.pruebatecnica.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pruebatecnica.application.dto.ClienteDTO;
import com.example.pruebatecnica.domain.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
    
    @Value("${error.tipoDocumentoObligatorio}")
    private String tipoDocumentoObligatorioMessage;
    
    @Value("${error.numeroDocumentoObligatorio}")
    private String numeroDocumentoObligatorioMessage;
    
    @Value("${error.tipoDocumentoInvalido}")
    private String tipoDocumentoInvalidoMessage;
    
    @Value("${request.clientNotFound}")
    private String notFoundMessage;

    @Override
    public Cliente consultarCliente(ClienteDTO clienteInfo) {

        Cliente cliente = new Cliente();

        if (clienteInfo.getTipoDocumento() == null || clienteInfo.getTipoDocumento().isEmpty()) {
            throw new IllegalArgumentException(tipoDocumentoObligatorioMessage);
        }

        if (clienteInfo.getNumeroDocumento() == null || clienteInfo.getNumeroDocumento().isEmpty()) {
            throw new IllegalArgumentException(numeroDocumentoObligatorioMessage);
        }

        if (!"C".equals(clienteInfo.getTipoDocumento()) && !"P".equals(clienteInfo.getTipoDocumento())) {
            throw new IllegalArgumentException(tipoDocumentoInvalidoMessage);
        }

        if (!"10121314".equals(clienteInfo.getNumeroDocumento())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundMessage);
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
