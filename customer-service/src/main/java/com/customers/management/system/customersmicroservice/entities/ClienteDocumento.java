package com.customers.management.system.customersmicroservice.entities;

import com.customers.management.system.customersmicroservice.util.enums.TipoDocumentoCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDocumento implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private TipoDocumentoCliente tipoDocumentoCliente;

    @Column
    private String documento;
}