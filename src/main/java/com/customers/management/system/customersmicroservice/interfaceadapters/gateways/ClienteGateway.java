package com.customers.management.system.customersmicroservice.interfaceadapters.gateways;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import com.customers.management.system.customersmicroservice.frameworks.db.ClienteRepository;
import com.customers.management.system.customersmicroservice.util.exception.ValidationsException;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteGateway {
    @Resource
    private ClienteRepository clienteRepository;

    @Resource
    private ClienteDocumentoGateway clienteDocumentoGateway;

    public Cliente insert(Cliente cliente){
        return this.clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente){
        return this.clienteRepository.save(cliente);
    }

    public Cliente disableCliente( Integer id) throws ValidationsException {
        Cliente clienteToDisable = this.findById(id);

        clienteToDisable.setAtivo(false);
        return this.clienteRepository.save(clienteToDisable);
    }
    public Cliente enableCliente( Integer id) throws ValidationsException {
        Cliente clienteToDisable = this.findById(id);

        clienteToDisable.setAtivo(false);

        return this.clienteRepository.save(clienteToDisable);
    }

    public Cliente findById(Integer id) throws ValidationsException {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new ValidationsException("0001", "Cliente"));
    }

    public Page<Cliente> findAll(Pageable pageable){
        return this.clienteRepository.findAll(pageable);
    }

}
