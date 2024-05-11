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
    private ClientePresenter clienteConverter;

    @Resource
    private ClienteGateway clienteGateway;

    @Resource
    private ClienteBusiness clienteBusiness;

    @Resource
    private ClienteDocumentoGateway clienteDocumentoGateway;

    public ClienteDto insert(ClienteDto clienteDto) throws ValidationsException{
        Cliente cliente = this.clienteConverter.convert(clienteDto);
        ClienteDocumento clienteDocumento;

        for (ClienteDocumento documento : cliente.getClienteDocumentos()) {
            clienteDocumento = this.clienteDocumentoGateway.findByDocumentoAndTipoDocumentoCliente(documento.getDocumento(),
                                                                                                    documento.getTipoDocumentoCliente());

            this.clienteBusiness.create(cliente, clienteDocumento);

        }

        cliente = this.clienteGateway.insert(cliente);

        return this.clienteConverter.convert(cliente);
    }

    public PagedResponse<ClienteDto> findAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getPageSize());

        Page<Cliente> clientes = this.clienteGateway.findAll(pageable);

        return this.clienteConverter.convert(clientes);
    }

    public ClienteDto findById(Integer id) throws ValidationsException{
        Cliente cliente = this.clienteGateway.findById(id);

        return this.clienteConverter.convert(cliente);
    }


    public ClienteDto findByDocument(String document) throws ValidationsException{
        Cliente cliente = this.clienteGateway.findByDocument(document);

        return this.clienteConverter.convert(cliente);
    }

    public void disable(Integer id) throws ValidationsException{

        Cliente cliente = this.clienteGateway.findById(id);
        cliente.setAtivo(false);
        this.clienteGateway.update(cliente);

    }

    public void enable(Integer id) throws ValidationsException{

        Cliente cliente = this.clienteGateway.findById(id);
        cliente.setAtivo(true);
        this.clienteGateway.update(cliente);

    }


}
