package com.customers.management.system.customersmicroservice.frameworks.web;

import com.customers.management.system.customersmicroservice.interfaceadapters.controllers.ClienteController;
import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos.ClienteDto;
import com.customers.management.system.customersmicroservice.util.exception.ValidationsException;
import com.customers.management.system.customersmicroservice.util.pagination.PagedResponse;
import com.customers.management.system.customersmicroservice.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Consultar um Cliente por código")
    @GetMapping(value = "/{idCliente}")
    public ResponseEntity<ClienteDto> findById(@Parameter(description = "Informe o ID do Cliente", example = "4")
                                                  @PathVariable Integer idCliente) {

        return ResponseEntity.ok(this.clienteController.findById(idCliente));
    }

    @Operation(summary = "Consultar um Cliente por documento")
    @GetMapping(value = "/document/{document}")
    public ResponseEntity<ClienteDto> findById(@Parameter(description = "Informe o documento do Cliente", example = "99999999")
                                               @PathVariable String document) {

        return ResponseEntity.ok(this.clienteController.findByDocument(document));
    }

    @Operation(summary = "Incluir informações de um cliente")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClienteDto> insert(@RequestBody ClienteDto clienteDto) throws ValidationsException {
        ClienteDto clienteDtoSalvo = this.clienteController.insert(clienteDto);

        return ResponseEntity.ok(clienteDtoSalvo);
    }

    @Operation(summary= "Desativa o cadastro do cliente")
    @PutMapping(value = "/disable/{idCliente}")
    public ResponseEntity<ClienteDto> desativar(@Parameter(description = "Informe o ID do Cliente", example = "4")
                                                      @PathVariable Integer idCliente) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("message", "Cliente desabilitado");

        return new ResponseEntity<>(this.clienteController.disable(idCliente), httpHeaders, HttpStatus.OK);
    }

    @Operation(summary= "Reativa o cadastro do cliente")
    @PutMapping(value = "/enable/{idCliente}")
    public ResponseEntity<ClienteDto> ativar(@Parameter(description = "Informe o ID do Cliente", example = "4")
                                              @PathVariable Integer idCliente) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("message", "Cliente ativado");

         return new ResponseEntity<>(this.clienteController.enable(idCliente), httpHeaders, HttpStatus.OK);
    }

}
