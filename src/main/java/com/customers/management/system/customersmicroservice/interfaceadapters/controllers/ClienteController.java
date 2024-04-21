package com.customers.management.system.customersmicroservice.interfaceadapters.controllers;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import com.customers.management.system.customersmicroservice.entities.ClienteDocumento;
import com.customers.management.system.customersmicroservice.entities.ClienteEndereco;
import com.customers.management.system.customersmicroservice.interfaceadapters.gateways.ClienteDocumentoGateway;
import com.customers.management.system.customersmicroservice.interfaceadapters.gateways.ClienteEnderecoGateway;
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

    @Resource
    private ClienteEnderecoGateway clienteEnderecoGateway;


    public ClienteDto insert(ClienteDto clienteDto) throws ValidationsException{
        Cliente cliente = this.clienteConverter.convert(clienteDto);
        ClienteDocumento clienteDocumento;

        // Lógica para não duplicar o documento para algum cliente
        for (ClienteDocumento documento : cliente.getClienteDocumentos()){
            clienteDocumento = this.clienteDocumentoGateway.findByDocumentoAndTipoDocumentoCliente(
                    documento.getDocumento(),
                    documento.getTipoDocumentoCliente()
            );

            if (clienteDocumento != null && clienteDocumento.getId() != null){
                this.clienteBusiness.create(cliente);
            }
        }

        cliente = this.clienteGateway.insert(cliente);

        // atribuir o cliente no documento e também no endereço- por algum motivo não está salvando o id do cliente
        for (ClienteDocumento documento : cliente.getClienteDocumentos()){
            documento.setCliente(cliente);
            documento = this.clienteDocumentoGateway.insert(documento);
        }

        for (ClienteEndereco endereco : cliente.getEnderecos()){
            endereco.setCliente(cliente);
            endereco = this.clienteEnderecoGateway.insert(endereco);
        }

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




}
