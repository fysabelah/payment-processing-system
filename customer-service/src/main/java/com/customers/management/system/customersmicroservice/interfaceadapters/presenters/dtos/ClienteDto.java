package com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos;

import com.customers.management.system.customersmicroservice.util.enums.TipoPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDto extends Dto {

    @NotEmpty
    @Schema(example = "João das Couves")
    private String nome;

    @Schema(example = "true")
    private boolean ativo;

    @NotEmpty
    @Email
    @Schema(example = "joaoCouves@provedormail.com")
    private String email;

    @NotEmpty
    @Pattern(regexp = "\\+[0-9]{2} [0-9]{2} 9 [0-9]{4}-[0-9]{4}")
    @Schema(example = "+99 64 9 8598-7856")
    private String telefone;

    @NotEmpty
    @Schema(example = "PIX")
    private TipoPagamento tipoPagamentoPreferencial;

    @NotEmpty
    private List<ClienteEnderecoDto> clienteEnderecosDto;

    @NotEmpty
    private List<ClienteDocumentoDto> clienteDocumentosDto;
}
