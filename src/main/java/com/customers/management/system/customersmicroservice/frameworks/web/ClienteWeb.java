package com.customers.management.system.customersmicroservice.frameworks.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/customer")
@Tag(name="Cadastro de Clientes", description = "Metódos para incluir, alterar e inativar ou listar cliente(s)")

public class ClienteWeb {


}
