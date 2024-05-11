package com.customers.management.system.customersmicroservice.entities;

import com.customers.management.system.customersmicroservice.util.enums.TipoDocumentoCliente;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table
public class ClienteDocumento implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    //@Column
    @Enumerated(value = EnumType.STRING)
    private TipoDocumentoCliente tipoDocumentoCliente;

    @Column
    private String documento;
}