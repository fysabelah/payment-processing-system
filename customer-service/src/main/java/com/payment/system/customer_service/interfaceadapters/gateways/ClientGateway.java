package com.payment.system.customer_service.interfaceadapters.gateways;

import com.payment.system.customer_service.entities.Client;
import com.payment.system.customer_service.frameworks.db.ClientRepository;
import com.payment.system.customer_service.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientGateway {

    private final ClientRepository repository;

    public ClientGateway(ClientRepository repository) {
        this.repository = repository;
    }

    public Client insert(Client client) {
        return repository.save(client);
    }

    public Client findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MessageUtil.getMessage("0001")));
    }

    public Optional<Client> findByDocumentOptional(String document) {
        return repository.findByDocument(document);
    }

    public Client findByDocument(String document) {
        return repository.findByDocument(document)
                .orElseThrow(() -> new NoSuchElementException(MessageUtil.getMessage("0001")));
    }

    public Page<Client> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Client update(Client client) {
        return repository.save(client);
    }
}
