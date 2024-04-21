package com.customers.management.system.customersmicroservice.interfaceadapters.gateways;

import com.customers.management.system.customersmicroservice.entities.ClienteDocumento;
import com.customers.management.system.customersmicroservice.frameworks.db.ClienteDocumentoRepository;
import com.customers.management.system.customersmicroservice.util.enums.TipoDocumentoCliente;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ClienteDocumentoGateway {

    @Resource
    private ClienteDocumentoRepository clienteDocumentoRepository;

    public ClienteDocumento findByDocumentoAndTipoDocumentoCliente(String documento, TipoDocumentoCliente tipoDocumentoCliente){
        return this.clienteDocumentoRepository.findByDocumentoAndTipoDocumentoCliente(documento,tipoDocumentoCliente);
    }
}
