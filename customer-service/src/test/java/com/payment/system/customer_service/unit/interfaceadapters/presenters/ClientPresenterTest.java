package com.payment.system.customer_service.unit.interfaceadapters.presenters;

import com.payment.system.customer_service.TestUtils;
import com.payment.system.customer_service.entities.Client;
import com.payment.system.customer_service.interfaceadapters.presenters.AddressPresenter;
import com.payment.system.customer_service.interfaceadapters.presenters.ClientPresenter;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.ClientDto;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.CreateClientDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ClientPresenterTest extends TestUtils {

    private static final String BASE_PATH = "src/test/java/com/payment/system/customer_service/unit/interfaceadapters/presenters/mocks/client/";

    private static ClientPresenter presenter;

    @BeforeAll
    static void beforeAll() {
        presenter = new ClientPresenter(new AddressPresenter());
    }

    @Test
    void convertCreateDtoToClass() throws IOException {
        CreateClientDto dto = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "client-create-dto.json"),
                CreateClientDto.class
        );

        Client shouldBe = presenter.convert(dto);

        Client expected = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "client-create.json"),
                Client.class
        );

        assertThat(shouldBe)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void convertDtoToClass() throws IOException {
        ClientDto dto = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "client-dto.json"),
                ClientDto.class
        );

        Client shouldBe = presenter.convert(dto);

        Client expected = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "client.json"),
                Client.class
        );

        assertThat(shouldBe)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void convertClassToDto() throws IOException {
        Client entity = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "client.json"),
                Client.class
        );

        ClientDto shouldBe = presenter.convert(entity);

        ClientDto expected = super.objectMapper.readValue(
                super.getMock(BASE_PATH + "client-dto.json"),
                ClientDto.class
        );

        assertThat(shouldBe)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}