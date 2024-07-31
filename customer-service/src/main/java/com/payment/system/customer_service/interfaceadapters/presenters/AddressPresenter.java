package com.payment.system.customer_service.interfaceadapters.presenters;

import com.payment.system.customer_service.entities.Address;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressPresenter implements Presenter<Address, AddressDto> {

    @Override
    public AddressDto convert(Address entity) {
        return AddressDto.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .zipCode(entity.getZipCode())
                .build();
    }

    @Override
    public Address convert(AddressDto dto) {
        return Address.builder()
                .id(dto.getId())
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .zipCode(dto.getZipCode())
                .build();
    }
}
