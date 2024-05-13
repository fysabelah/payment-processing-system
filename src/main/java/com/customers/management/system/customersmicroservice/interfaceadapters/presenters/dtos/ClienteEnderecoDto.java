package com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos;

import com.customers.management.system.customersmicroservice.util.enums.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClienteEnderecoDto extends Dto {

    @NotNull
    private TipoEndereco tipoEndereco;

    @NotEmpty
    @Schema(description = "Rua/Avenida", example = "Rua 34")
    private String logradouro;

    @NotEmpty
    @Schema(description = "Número do lote", example = "95")
    private String numero;

    @NotEmpty
    @Schema(description = "Quadra/Bairro", example = "qd 735")
    private String bairro;

    @NotEmpty
    @Schema(description = "Cidade", example = "São Paulo")
    private String cidade;

    @NotEmpty
    @Schema(description = "Estado", example = "São Paulo")
    private String estado;

    @NotEmpty
    @Schema(description = "Orientações/Pontos de referência", example = "Próximo ao mercado Quintada")
    private String complemento;

    @NotEmpty
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
    private String cep;
}