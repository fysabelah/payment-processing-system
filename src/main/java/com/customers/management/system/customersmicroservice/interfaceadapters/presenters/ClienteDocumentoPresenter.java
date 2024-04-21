package com.customers.management.system.customersmicroservice.interfaceadapters.presenters;

import com.customers.management.system.customersmicroservice.entities.ClienteDocumento;
import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos.ClienteDocumentoDto;
import org.springframework.stereotype.Component;

@Component
public class ClienteDocumentoPresenter implements Presenter<ClienteDocumento, ClienteDocumentoDto> {
    @Override
    public ClienteDocumentoDto convert(ClienteDocumento entitie){

        ClienteDocumentoDto dto = new ClienteDocumentoDto();

        dto.setId(entitie.getId());

        dto.setDocumento(entitie.getDocumento());
        dto.setTipoDocumentoCliente(entitie.getTipoDocumentoCliente());

        return dto;
    }

    @Override
    public ClienteDocumento convert(ClienteDocumentoDto dto){

        ClienteDocumento entitie = new ClienteDocumento();

        entitie.setId(dto.getId());

        entitie.setDocumento(dto.getDocumento());
        entitie.setTipoDocumentoCliente(dto.getTipoDocumentoCliente());

        return entitie;
    }
}
