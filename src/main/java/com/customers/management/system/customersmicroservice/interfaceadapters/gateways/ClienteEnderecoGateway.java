package com.customers.management.system.customersmicroservice.interfaceadapters.gateways;

import com.customers.management.system.customersmicroservice.entities.ClienteEndereco;
import com.customers.management.system.customersmicroservice.frameworks.db.ClienteEnderecoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ClienteEnderecoGateway {

    @Resource
    private ClienteEnderecoRepository clienteEnderecoRepository;

    public ClienteEndereco insert( ClienteEndereco clienteEndereco){
        return this.clienteEnderecoRepository.save(clienteEndereco);
    }
}
