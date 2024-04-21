package com.customers.management.system.customersmicroservice.frameworks.db;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
