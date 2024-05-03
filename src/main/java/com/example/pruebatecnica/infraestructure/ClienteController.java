package com.example.pruebatecnica.infraestructure;

import org.springframework.web.bind.annotation.RestController;

import com.example.pruebatecnica.application.dto.ClienteDTO;
import com.example.pruebatecnica.application.dto.ClienteResponseDTO;
import com.example.pruebatecnica.application.service.ClienteServiceImpl;
import com.example.pruebatecnica.domain.entity.Cliente;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
public class ClienteController {

    @Value("${request.clientFound}")
    private String clientFound;

    @Value("${request.clientNotFound}")
    private String clientNotFound;
    
    @Value("${error.invalidRequest}")
    private String invalidRequest;

    @Value("${error.internalServer}")
    private String errorInternalServer;

    @Autowired
    ClienteServiceImpl clienteServiceImpl = new ClienteServiceImpl();

    @PostMapping("consultarCliente")
    public ResponseEntity<ClienteResponseDTO> consultarCliente(@RequestBody ClienteDTO clienteDTO) {

        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();

        try {
            Cliente clienteEncontado = clienteServiceImpl.consultarCliente(clienteDTO);

            clienteResponseDTO.setCode(200);
            clienteResponseDTO.setData(clienteEncontado);
            clienteResponseDTO.setMsj(clientFound);

            return ResponseEntity.ok(clienteResponseDTO);

        } catch (IllegalArgumentException e) {
            log.error(invalidRequest, e.getMessage());
            clienteResponseDTO.setCode(400);
            clienteResponseDTO.setMsj(invalidRequest + ": " + e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clienteResponseDTO);
        } catch (RuntimeException e) {
            log.error(clientNotFound, e.getMessage());
            clienteResponseDTO.setCode(404);
            clienteResponseDTO.setMsj(clientNotFound);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteResponseDTO);
        } catch (Exception e) {
            log.error(errorInternalServer, e.getMessage());
            clienteResponseDTO.setCode(500);
            clienteResponseDTO.setMsj(errorInternalServer);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(clienteResponseDTO);
        }
    }

}
