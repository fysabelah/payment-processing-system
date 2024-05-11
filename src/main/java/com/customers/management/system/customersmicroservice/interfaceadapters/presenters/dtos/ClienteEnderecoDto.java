package com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos;

import com.customers.management.system.customersmicroservice.util.enums.TipoEndereco;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ClienteEnderecoDto extends Dto implements Serializable {
    private Integer id;

    @NotNull
    private TipoEndereco tipoEndereco;

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private String cep;
}