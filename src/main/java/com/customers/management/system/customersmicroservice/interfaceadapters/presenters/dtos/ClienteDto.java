package com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos;

import com.customers.management.system.customersmicroservice.util.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"id"})

public class ClienteDto extends Dto implements Serializable {
    private List<ClienteEnderecoDto> clienteEnderecosDto;

    private List<ClienteDocumentoDto> clienteDocumentosDto;

    @NotEmpty
    @Schema(example = "PIX")
    private TipoPagamento tipoPagamentoPreferencial;

    @NotEmpty
    @Schema(example = "João das Couves")
    private String nome;

    @NotEmpty
    @Schema(example = "george@mail.com.br")
    private String email;

    @NotEmpty
    @Schema(example = "11980809090")
    private String telefone;

    @NotEmpty
    @Schema(example = "True")
    private boolean ativo;

}
