package com.customers.management.system.customersmicroservice.entities;

import com.customers.management.system.customersmicroservice.util.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Cliente implements Serializable {

    private static final long serialVersionUID=1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    //@Column
    @Enumerated(value=EnumType.STRING)
    private TipoPagamento tipoPagamentoPreferencial;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String telefone;

    @Column
    private boolean ativo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="cliente_id")
    private List<ClienteDocumento> clienteDocumentos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="cliente_id")
    private List<ClienteEndereco> enderecos;

    public List<ClienteDocumento> getClienteDocumentos(){
        if (this.clienteDocumentos==null){
            return new ArrayList<>();
        }
        return clienteDocumentos;
    }

    public List<ClienteEndereco> getClienteEnderecos(){

        if (this.getClienteEnderecos()==null){
            return new ArrayList<>();
        }

        return getClienteEnderecos();

    }

}
