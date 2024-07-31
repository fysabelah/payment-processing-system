package com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos;

import com.customers.management.system.customersmicroservice.util.enums.TipoDocumentoCliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ClienteDocumentoDto extends Dto {

    @NotNull
    private TipoDocumentoCliente tipoDocumentoCliente;

    @NotEmpty
    @Schema(example = "759.698.785-89")
    private String documento;
}