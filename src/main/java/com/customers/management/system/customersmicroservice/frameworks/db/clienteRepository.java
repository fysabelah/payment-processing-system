package com.customers.management.system.customersmicroservice.frameworks.db;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface clienteRepository extends JpaRepository<Cliente, String> {

    Optional<Cliente> findByDocumento(String documento);

    List<Cliente> findClienteByNomeEqualsIgnoreCase(String documento);

}
