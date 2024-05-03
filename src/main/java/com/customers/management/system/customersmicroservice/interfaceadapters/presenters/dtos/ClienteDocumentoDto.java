package com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos;

import com.customers.management.system.customersmicroservice.util.enums.TipoDocumentoCliente;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ClienteDocumentoDto extends Dto implements Serializable {
    @NotEmpty
    private Integer id;

    @NotEmpty
    private TipoDocumentoCliente tipoDocumentoCliente;

    @NotEmpty
    private String documento;
    //private ClienteDto clienteDto;


}