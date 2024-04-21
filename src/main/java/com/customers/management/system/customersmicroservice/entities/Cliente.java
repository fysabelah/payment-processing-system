package com.customers.management.system.customersmicroservice.entities;

import com.customers.management.system.customersmicroservice.util.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID=1;

    @Id
    private Integer Id;

    @Column
    private TipoPagamento tipoPagamentoPreferencial;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String telefone;

    @Column
    private boolean ativo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ClienteDocumento> clienteDocumentos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ClienteEndereco> enderecos;


}
