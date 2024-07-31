package com.customers.management.system.customersmicroservice.frameworks.db;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByDocumentos_Documento(String documento);

    List<Cliente> findAllByDocumentos_DocumentoIn(List<String> documents);
}
