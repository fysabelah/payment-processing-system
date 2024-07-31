package com.payment.system.customer_service.interfaceadapters.controllers;

import com.payment.system.customer_service.entities.Client;
import com.payment.system.customer_service.interfaceadapters.gateways.ClientGateway;
import com.payment.system.customer_service.interfaceadapters.presenters.ClientPresenter;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.ClientDto;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.CreateClientDto;
import com.payment.system.customer_service.usercase.ClientBusiness;
import com.payment.system.customer_service.util.exception.ValidationsException;
import com.payment.system.customer_service.util.pagination.PagedResponse;
import com.payment.system.customer_service.util.pagination.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientController {

    private final ClientPresenter presenter;

    private final ClientGateway gateway;

    private final ClientBusiness business;

    public ClientController(ClientPresenter presenter, ClientGateway gateway, ClientBusiness business) {
        this.presenter = presenter;
        this.gateway = gateway;
        this.business = business;
    }

    public int insert(CreateClientDto clientDto) throws ValidationsException {
        Client client = presenter.convert(clientDto);

        Optional<Client> saved = gateway.findByDocumentOptional(client.getDocument());

        business.validateCreation(saved, client);

        client = gateway.insert(client);

        return client.getId();
    }

    public PagedResponse<ClientDto> findAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getPageSize());

        Page<Client> clients = gateway.findAll(pageable);

        return this.presenter.convert(clients);
    }

    public ClientDto findById(Integer id) {
        Client client = gateway.findById(id);

        return this.presenter.convert(client);
    }


    public ClientDto findByDocument(String document) {
        Client client = gateway.findByDocument(document);

        return presenter.convert(client);
    }

    public ClientDto update(ClientDto clientDto, Integer id) throws ValidationsException {
        Client client = gateway.findById(id);

        Client newValues = presenter.convert(clientDto);

        business.update(client, newValues);

        client = gateway.update(client);

        return presenter.convert(client);
    }
}
