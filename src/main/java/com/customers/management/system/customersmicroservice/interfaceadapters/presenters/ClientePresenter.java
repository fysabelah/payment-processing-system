package com.customers.management.system.customersmicroservice.interfaceadapters.presenters;


import com.customers.management.system.customersmicroservice.entities.Cliente;
import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos.ClienteDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ClientePresenter implements Presenter<Cliente, ClienteDto> {

    @Resource
    ClienteEnderecoPresenter clienteEnderecoPresenter;

    @Resource
    ClienteDocumentoPresenter clienteDocumentoPresenter;

    @Override
    public ClienteDto convert(Cliente entitie){

        ClienteDto dto = new ClienteDto();

        dto.setId(entitie.getId());
        dto.setNome(entitie.getNome());
        dto.setTelefone(entitie.getTelefone());
        dto.setEmail(entitie.getEmail());
        dto.setAtivo(entitie.isAtivo());

        dto.setTipoPagamentoPreferencial(entitie.getTipoPagamentoPreferencial());

        dto.setClienteEnderecosDto(this.clienteEnderecoPresenter.convertEntity(entitie.getEnderecos()));
        dto.setClienteDocumentosDto(this.clienteDocumentoPresenter.convertEntity(entitie.getClienteDocumentos()));


        return dto;
    }

    @Override
    public Cliente convert(ClienteDto dto){

        Cliente entitie = new Cliente();

        entitie.setId(dto.getId());
        entitie.setNome(dto.getNome());
        entitie.setEmail(dto.getEmail());
        entitie.setTelefone(dto.getTelefone());
        entitie.setAtivo(dto.isAtivo());
        entitie.setTipoPagamentoPreferencial(dto.getTipoPagamentoPreferencial());

        entitie.setEnderecos(this.clienteEnderecoPresenter.convertDtos(dto.getClienteEnderecosDto()));
        entitie.setClienteDocumentos(this.clienteDocumentoPresenter.convertDtos(dto.getClienteDocumentosDto()));

        return entitie;
    }
}
