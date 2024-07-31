package com.customers.management.system.customersmicroservice.frameworks.db;

import com.customers.management.system.customersmicroservice.entities.ClienteDocumento;
import com.customers.management.system.customersmicroservice.util.enums.TipoDocumentoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteDocumentoRepository extends JpaRepository<ClienteDocumento, Integer> {
    ClienteDocumento findByDocumentoAndTipoDocumentoCliente(String documento, TipoDocumentoCliente tipoDocumentoCliente);

}
