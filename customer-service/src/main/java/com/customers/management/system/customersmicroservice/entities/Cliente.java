package com.customers.management.system.customersmicroservice.entities;

import com.customers.management.system.customersmicroservice.util.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private TipoPagamento tipoPagamentoPreferencial;

    @Column(nullable = false)
    private String nome;

    @Column
    private String email;

    @Column
    private String telefone;

    @Column(nullable = false)
    private boolean ativo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<ClienteDocumento> documentos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<ClienteEndereco> enderecos;

    public List<ClienteDocumento> getDocumentos() {
        if (this.documentos == null) {
            return new ArrayList<>();
        }

        return documentos;
    }

    public List<ClienteEndereco> getEnderecos() {
        if (this.enderecos == null) {
            return new ArrayList<>();
        }

        return enderecos;
    }
}
