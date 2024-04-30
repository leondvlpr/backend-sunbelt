package com.example.pruebatecnica.infraestructure;

import org.springframework.web.bind.annotation.RestController;

import com.example.pruebatecnica.application.dto.ClienteDTO;
import com.example.pruebatecnica.application.dto.ClienteResponseDTO;
import com.example.pruebatecnica.application.service.ClienteServiceImpl;
import com.example.pruebatecnica.domain.entity.Cliente;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteServiceImpl = new ClienteServiceImpl();

    @PostMapping("consultarCliente")
    public ResponseEntity<ClienteResponseDTO> consultarCliente(@RequestBody ClienteDTO clienteDTO) {

        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();

        try {
            Cliente clienteEncontado = clienteServiceImpl.consultarCliente(clienteDTO);

            clienteResponseDTO.setCode(200);
            clienteResponseDTO.setData(clienteEncontado);
            clienteResponseDTO.setMsj("Cliente encontado");

            return ResponseEntity.ok(clienteResponseDTO);

        } catch (IllegalArgumentException e) {
            clienteResponseDTO.setCode(400);
            clienteResponseDTO.setMsj("Petición inválida" + ": " + e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clienteResponseDTO);
        } catch (RuntimeException e) {
            clienteResponseDTO.setCode(404);
            clienteResponseDTO.setMsj("Cliente no encontrado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteResponseDTO);
        } catch (Exception e) {
            log.error("Error interno del servidor", e.getMessage());
            clienteResponseDTO.setCode(500);
            clienteResponseDTO.setMsj("Error interno del servidor");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(clienteResponseDTO);
        }
    }

}
