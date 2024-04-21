package com.customers.management.system.customersmicroservice.entities;

import com.customers.management.system.customersmicroservice.util.enums.TipoDocumentoCliente;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="clienteDocumento")
public class ClienteDocumento implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @Column
    private TipoDocumentoCliente tipoDocumentoCliente;

    @Column
    private String documento;
}