package com.payment.system.customer_service.unit.interfaceadapters.controllers;

import com.payment.system.customer_service.entities.Client;
import com.payment.system.customer_service.interfaceadapters.controllers.ClientController;
import com.payment.system.customer_service.interfaceadapters.gateways.ClientGateway;
import com.payment.system.customer_service.interfaceadapters.presenters.ClientPresenter;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.ClientDto;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.CreateClientDto;
import com.payment.system.customer_service.usercase.ClientBusiness;
import com.payment.system.customer_service.util.exception.ValidationsException;
import com.payment.system.customer_service.util.pagination.PagedResponse;
import com.payment.system.customer_service.util.pagination.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientControllerTest {

    @Mock
    private ClientPresenter presenter;

    @Mock
    private ClientGateway gateway;

    @Mock
    private ClientBusiness business;

    @InjectMocks
    private ClientController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() throws ValidationsException {
        CreateClientDto createClientDto = new CreateClientDto();
        Client client = new Client();
        client.setId(1);
        client.setDocument("123456");

        when(presenter.convert(createClientDto))
                .thenReturn(client);
        when(gateway.findByDocumentOptional(client.getDocument()))
                .thenReturn(Optional.empty());
        when(gateway.insert(client))
                .thenReturn(client);

        int result = controller.insert(createClientDto);

        assertEquals(1, result);
        verify(business)
                .validateCreation(Optional.empty(), client);
        verify(gateway)
                .insert(client);
    }

    @Test
    void testFindAll() {
        Pagination pagination = new Pagination();
        pagination.setPage(0);
        pagination.setPageSize(10);

        Pageable pageable = PageRequest.of(0, 10);
        Client client = new Client();
        Page<Client> clientsPage = new PageImpl<>(Arrays.asList(client));
        PagedResponse<ClientDto> pagedResponse = new PagedResponse<>();

        when(gateway.findAll(pageable))
                .thenReturn(clientsPage);
        when(presenter.convert(clientsPage))
                .thenReturn(pagedResponse);

        PagedResponse<ClientDto> result = controller.findAll(pagination);

        assertEquals(pagedResponse, result);
        verify(gateway).findAll(pageable);
        verify(presenter).convert(clientsPage);
    }

    @Test
    void testFindById() {
        int id = 1;
        Client client = new Client();
        ClientDto clientDto = new ClientDto();

        when(gateway.findById(id))
                .thenReturn(client);
        when(presenter.convert(client))
                .thenReturn(clientDto);

        ClientDto result = controller.findById(id);

        assertEquals(clientDto, result);
        verify(gateway).findById(id);
        verify(presenter).convert(client);
    }

    @Test
    void testFindByDocument() {
        String document = "123456";
        Client client = new Client();
        ClientDto clientDto = new ClientDto();

        when(gateway.findByDocument(document))
                .thenReturn(client);
        when(presenter.convert(client))
                .thenReturn(clientDto);

        ClientDto result = controller.findByDocument(document);

        assertEquals(clientDto, result);
        verify(gateway).findByDocument(document);
        verify(presenter).convert(client);
    }

    @Test
    void testUpdate() throws ValidationsException {
        int id = 1;
        ClientDto clientDto = new ClientDto();
        Client client = new Client();
        Client newValues = new Client();

        when(gateway.findById(id))
                .thenReturn(client);
        when(presenter.convert(clientDto))
                .thenReturn(newValues);
        when(gateway.update(client))
                .thenReturn(client);
        when(presenter.convert(client))
                .thenReturn(clientDto);

        ClientDto result = controller.update(clientDto, id);

        assertEquals(clientDto, result);
        verify(gateway).findById(id);
        verify(business).update(client, newValues);
        verify(gateway).update(client);
        verify(presenter).convert(client);
    }
}