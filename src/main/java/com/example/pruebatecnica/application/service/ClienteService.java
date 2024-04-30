package com.example.pruebatecnica.application.service;

import com.example.pruebatecnica.application.dto.ClienteDTO;
import com.example.pruebatecnica.domain.entity.Cliente;

public interface ClienteService {

    Cliente consultarCliente(ClienteDTO clientInfo);

}
