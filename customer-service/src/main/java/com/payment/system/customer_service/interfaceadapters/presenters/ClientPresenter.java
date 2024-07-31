package com.payment.system.customer_service.interfaceadapters.presenters;


import com.payment.system.customer_service.entities.Address;
import com.payment.system.customer_service.entities.Client;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.ClientDto;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.CreateClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientPresenter implements Presenter<Client, ClientDto> {

    private final AddressPresenter presenter;

    public ClientPresenter(AddressPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ClientDto convert(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .document(client.getDocument())
                .cellphone(client.getCellphone())
                .email(client.getEmail())
                .address(presenter.convert(client.getAddress()))
                .build();
    }

    @Override
    public Client convert(ClientDto dto) {
        return Client.builder()
                .id(dto.getId())
                .name(dto.getName())
                .document(dto.getDocument())
                .cellphone(dto.getCellphone())
                .email(dto.getEmail())
                .address(presenter.convert(dto.getAddress()))
                .build();
    }

    public Client convert(CreateClientDto dto) {
        return Client.builder()
                .name(dto.getName())
                .document(dto.getDocument())
                .email(dto.getEmail())
                .cellphone(dto.getCellphone())
                .address(
                        Address.builder()
                                .street(dto.getStreet())
                                .city(dto.getCity())
                                .state(dto.getState())
                                .country(dto.getCountry())
                                .zipCode(dto.getZipCode())
                                .build()
                )
                .build();
    }
}
