package com.payment.system.customer_service.interfaceadapters.presenters;

import com.payment.system.customer_service.TestUtils;
import com.payment.system.customer_service.entities.Address;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.AddressDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class AddressPresenterTest extends TestUtils {

    private static final String BASE_PATH = "src/test/java/com/payment/system/customer_service/interfaceadapters/presenters/mocks/address/";


    @Test
    void convertDtoToClass() throws IOException {
        AddressPresenter presenter = new AddressPresenter();

        AddressDto addressDto = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "address-dto.json"),
                AddressDto.class
        );

        Address shouldBe = presenter.convert(addressDto);

        Address expected = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "address.json"),
                Address.class
        );

        assertThat(shouldBe)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void convertClassToDto() throws IOException {
        AddressPresenter presenter = new AddressPresenter();

        Address address = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "address.json"),
                Address.class
        );


        AddressDto shouldBe = presenter.convert(address);

        AddressDto expected = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "address-dto.json"),
                AddressDto.class
        );

        assertThat(shouldBe)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}