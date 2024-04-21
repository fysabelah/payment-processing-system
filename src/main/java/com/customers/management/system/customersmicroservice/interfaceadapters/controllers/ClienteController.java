package com.customers.management.system.customersmicroservice.interfaceadapters.controllers;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import com.customers.management.system.customersmicroservice.entities.ClienteDocumento;
import com.customers.management.system.customersmicroservice.interfaceadapters.gateways.ClienteDocumentoGateway;
import com.customers.management.system.customersmicroservice.interfaceadapters.gateways.ClienteGateway;
import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.ClientePresenter;
import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos.ClienteDto;
import com.customers.management.system.customersmicroservice.interfaceadapters.usercase.ClienteBusiness;
import com.customers.management.system.customersmicroservice.util.exception.ValidationsException;
import com.customers.management.system.customersmicroservice.util.pagination.PagedResponse;
import com.customers.management.system.customersmicroservice.util.pagination.Pagination;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ClienteController {
    @Resource
    private ClienteGateway clienteGateway;

    @Resource
    private ClienteBusiness clienteBusiness;

    @Resource
    private ClienteDocumentoGateway clienteDocumentoGateway;

    @Resource
    private ClientePresenter clienteConverter;

    public ClienteDto insert(ClienteDto clienteDto) throws ValidationsException{
        Cliente cliente = this.clienteConverter.convert(clienteDto);
        ClienteDocumento clienteDocumento;

        // Lógica para não duplicar o documento para algum cliente
        for (ClienteDocumento documento : cliente.getClienteDocumentos()){
            clienteDocumento = this.clienteDocumentoGateway.findByDocumentoAndTipoDocumentoCliente(
                    documento.getDocumento(),
                    documento.getTipoDocumentoCliente()
            );

            if (clienteDocumento != null){
                this.clienteBusiness.create(clienteDocumento.getCliente());
            }
        }

        cliente = this.clienteGateway.insert(cliente);
        return this.clienteConverter.convert(cliente);
    }


    public PagedResponse<ClienteDto> findAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getPageSize());

        Page<Cliente> clientes = this.clienteGateway.findAll(pageable);

        return this.clienteConverter.convert(clientes);
    }
}
