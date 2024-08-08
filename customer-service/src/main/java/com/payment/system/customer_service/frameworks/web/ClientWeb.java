package com.payment.system.customer_service.frameworks.web;

import com.payment.system.customer_service.interfaceadapters.controllers.ClientController;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.ClientDto;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.CreateClientDto;
import com.payment.system.customer_service.util.exception.ValidationsException;
import com.payment.system.customer_service.util.pagination.PagedResponse;
import com.payment.system.customer_service.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Clientes", description = "Metódos para operações com clientes")
public class ClientWeb {

    private final ClientController controller;

    public ClientWeb(ClientController controller) {
        this.controller = controller;
    }

    @Operation(summary = "Consultar todos os clientes")
    @GetMapping(produces = "application/json")
    public ResponseEntity<PagedResponse<ClientDto>> findAll(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                                                            @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage) {
        Pagination page = new Pagination(initialPage, pageSize);

        return ResponseEntity.ok(controller.findAll(page));
    }

    @Operation(summary = "Consultar um por documento")
    @GetMapping(value = "/document/{document}", produces = "application/json")
    public ResponseEntity<ClientDto> findByDocument(@Parameter(description = "Documento do cliente", example = "11111100059")
                                                    @PathVariable String document) {
        return ResponseEntity.ok(controller.findByDocument(document));
    }

    @Operation(summary = "Consultar um por identificador")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findById(@Parameter(description = "Identificador do cliente", example = "4")
                                              @PathVariable Integer id) {
        return ResponseEntity.ok(controller.findById(id));
    }

    @Operation(summary = "Incluir informações de um cliente")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientDto> insert(@RequestBody @Valid CreateClientDto clientDto) throws ValidationsException {
        int id = controller.insert(clientDto);

        return ResponseEntity.ok()
                .header("id_cliente", Integer.toString(id))
                .build();
    }

    @Operation(summary = "Atualizar informações do cliente")
    @PutMapping(value = "/{id}",consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientDto> update(@RequestBody @Valid ClientDto clientDto, @PathVariable Integer id) throws ValidationsException {
        return ResponseEntity.ok(controller.update(clientDto, id));
    }
}
