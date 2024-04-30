package com.example.pruebatecnica.application.dto;

import com.example.pruebatecnica.domain.entity.Cliente;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    Integer code;
    String msj;
    Cliente data;
}
