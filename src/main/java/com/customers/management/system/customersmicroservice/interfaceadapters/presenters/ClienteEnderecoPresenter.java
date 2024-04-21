package com.customers.management.system.customersmicroservice.interfaceadapters.presenters;

import com.customers.management.system.customersmicroservice.entities.ClienteEndereco;
import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos.ClienteEnderecoDto;
import org.springframework.stereotype.Component;
@Component
public class ClienteEnderecoPresenter implements Presenter<ClienteEndereco, ClienteEnderecoDto> {

    @Override
    public ClienteEnderecoDto convert(ClienteEndereco entitie){

        ClienteEnderecoDto dto = new ClienteEnderecoDto();

        dto.setId(entitie.getId());

        dto.setTipoEndereco(entitie.getTipoEndereco());
        dto.setLogradouro(entitie.getLogradouro());
        dto.setNumero(entitie.getNumero());
        dto.setBairro(entitie.getBairro());
        dto.setCidade(entitie.getCidade());
        dto.setEstado(entitie.getEstado());
        dto.setComplemento(entitie.getComplemento());
        dto.setCep(entitie.getCep());

        return dto;
    }
    @Override
    public ClienteEndereco convert(ClienteEnderecoDto dto){
        ClienteEndereco entitie = new ClienteEndereco();

        entitie.setId(dto.getId());

        entitie.setTipoEndereco(dto.getTipoEndereco());
        entitie.setLogradouro(dto.getLogradouro());
        entitie.setNumero(dto.getNumero());
        entitie.setBairro(dto.getBairro());
        entitie.setCidade(dto.getCidade());
        entitie.setEstado(dto.getEstado());
        entitie.setComplemento(dto.getComplemento());
        entitie.setCep(dto.getCep());

        return entitie;
    }
}
