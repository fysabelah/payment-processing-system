package com.customers.management.system.customersmicroservice.interfaceadapters.presenters;

import com.customers.management.system.customersmicroservice.entities.ClienteDocumento;
import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos.ClienteDocumentoDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ClienteDocumentoPresenter implements Presenter<ClienteDocumento, ClienteDocumentoDto> {

    @Resource
    private ClientePresenter clientePresenter;

    @Override
    public ClienteDocumentoDto convert(ClienteDocumento entitie){

        ClienteDocumentoDto dto = new ClienteDocumentoDto();

        dto.setId(entitie.getId());

        dto.setDocumento(entitie.getDocumento());
        dto.setTipoDocumentoCliente(entitie.getTipoDocumentoCliente());

        dto.setClienteDto(this.clientePresenter.convert(entitie.getCliente()));

        return dto;
    }

    @Override
    public ClienteDocumento convert(ClienteDocumentoDto dto){

        ClienteDocumento entitie = new ClienteDocumento();

        entitie.setId(dto.getId());

        entitie.setDocumento(dto.getDocumento());
        entitie.setTipoDocumentoCliente(dto.getTipoDocumentoCliente());

        entitie.setCliente(this.clientePresenter.convert(dto.getClienteDto()));

        return entitie;
    }
}
