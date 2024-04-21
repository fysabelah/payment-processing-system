package com.customers.management.system.customersmicroservice.frameworks.web;

import com.customers.management.system.customersmicroservice.interfaceadapters.controllers.ClienteController;
import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos.ClienteDto;
import com.customers.management.system.customersmicroservice.util.pagination.PagedResponse;
import com.customers.management.system.customersmicroservice.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/customer")
@Tag(name="Cadastro de Clientes", description = "Metódos para listar cliente(s), incluir, alterar e inativar")

public class ClienteWeb {

    @Resource
    private ClienteController clienteController;

    @Operation(summary = "Consultar todos os clientes")
    @GetMapping
    public ResponseEntity<PagedResponse<ClienteDto>> findAll(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                                                             @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage) {

        Pagination page = new Pagination(initialPage, pageSize);

        return ResponseEntity.ok(this.clienteController.findAll(page));
    }


}
