package com.customers.management.system.customersmicroservice.interfaceadapters.gateways;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import com.customers.management.system.customersmicroservice.frameworks.db.ClienteRepository;
import com.customers.management.system.customersmicroservice.util.MessageUtil;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClienteGateway {
    @Resource
    private ClienteRepository clienteRepository;

    public Cliente insert(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public Cliente disable(Cliente cliente) {
        cliente.setAtivo(false);

        return this.clienteRepository.save(cliente);
    }

    public Cliente enable(Cliente cliente) {
        cliente.setAtivo(true);

        return this.clienteRepository.save(cliente);
    }

    public Cliente findById(Integer id) {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MessageUtil.getMessage("0001", "Cliente")));
    }

    public Cliente findByDocument(String document) {
        return this.clienteRepository.findByDocumentos_Documento(document)
                .orElseThrow(() -> new NoSuchElementException(MessageUtil.getMessage("0001", "Cliente")));

    }

    public Page<Cliente> findAll(Pageable pageable) {
        return this.clienteRepository.findAll(pageable);
    }

    public List<Cliente> findAllByDocuments(List<String> documents) {
        return clienteRepository.findAllByDocumentos_DocumentoIn(documents);
    }
}
