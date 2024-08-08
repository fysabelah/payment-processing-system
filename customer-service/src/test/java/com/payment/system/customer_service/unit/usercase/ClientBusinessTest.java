package com.payment.system.customer_service.unit.usercase;

import com.payment.system.customer_service.entities.Address;
import com.payment.system.customer_service.entities.Client;
import com.payment.system.customer_service.usercase.ClientBusiness;
import com.payment.system.customer_service.util.MessageUtil;
import com.payment.system.customer_service.util.exception.ValidationsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientBusinessTest {

    private static ClientBusiness business;

    @BeforeAll
    static void beforeAll() {
        business = new ClientBusiness();
    }

    @Test
    void testValidateCreationWithPresentOptional() {
        Client client = mock(Client.class);
        Optional<Client> optionalClient = Optional.of(client);

        ValidationsException thrown = assertThrows(ValidationsException.class, () -> business.validateCreation(optionalClient, client));

        assertEquals(MessageUtil.getMessage("0002"), thrown.getMessage());
    }

    @Test
    void testValidateCreationWithNullDocument() {
        Client client = new Client();
        client.setDocument(null);
        client.setName("Name");
        client.setCellphone("123456789");
        client.setAddress(new Address());

        Optional<Client> optionalClient = Optional.empty();

        ValidationsException thrown = assertThrows(ValidationsException.class, () -> business.validateCreation(optionalClient, client));

        assertEquals(MessageUtil.getMessage("0003"), thrown.getMessage());
    }

    @Test
    void testValidateCreationWithBlankName() {
        Client client = new Client();
        client.setDocument("Document");
        client.setName("");
        client.setCellphone("123456789");
        client.setAddress(new Address());

        Optional<Client> optionalClient = Optional.empty();

        ValidationsException thrown = assertThrows(ValidationsException.class, () -> business.validateCreation(optionalClient, client));

        assertEquals(MessageUtil.getMessage("0004"), thrown.getMessage());
    }

    @Test
    void testValidateCreationWithBlankCellphone() {
        Client client = new Client();
        client.setDocument("Document");
        client.setName("Name");
        client.setCellphone("");
        client.setAddress(new Address());

        Optional<Client> optionalClient = Optional.empty();

        ValidationsException thrown = assertThrows(ValidationsException.class, () -> business.validateCreation(optionalClient, client));

        assertEquals(MessageUtil.getMessage("0005"), thrown.getMessage());
    }

    @Test
    void testValidateCreationWithNullAddress() {
        Client client = new Client();
        client.setDocument("Document");
        client.setName("Name");
        client.setCellphone("123456789");
        client.setAddress(null);

        Optional<Client> optionalClient = Optional.empty();

        ValidationsException thrown = assertThrows(ValidationsException.class, () -> business.validateCreation(optionalClient, client));

        assertEquals(MessageUtil.getMessage("0006"), thrown.getMessage());
    }

    @Test
    void testValidateCreationValidClient() {
        Client client = new Client();
        client.setDocument("Document");
        client.setName("Name");
        client.setCellphone("123456789");
        client.setAddress(new Address());

        Optional<Client> optionalClient = Optional.empty();

        assertDoesNotThrow(() -> business.validateCreation(optionalClient, client));
    }

    @Test
    void update() throws ValidationsException {
        Client old = new Client();
        old.setId(23);
        old.setEmail("email@gmail.com");
        old.setDocument("896.989.963.89");
        old.setName("Eu sou um teste");
        old.setCellphone("55 62 9 9999-0000");
        old.setAddress(new Address(59, "rua", "cidade", "estado", "pais", "26598-898"));

        Client newValues = new Client();
        newValues.setId(23);
        newValues.setEmail("email@gmail.com");
        newValues.setDocument("896.989.963.55");
        newValues.setName("Eu Sou Teste");
        newValues.setCellphone("55 62 9 9999-0001");
        newValues.setAddress(new Address(59, "rua 3", "cidade", "estado 5", "pais", "26598-898"));

        business.update(old, newValues);

        assertEquals(23, old.getId());
        assertEquals("email@gmail.com", old.getEmail());
        assertEquals("896.989.963.89", old.getDocument());
        assertEquals("Eu Sou Teste", old.getName());
        assertEquals("55 62 9 9999-0001", old.getCellphone());
        assertEquals(newValues.getAddress(), old.getAddress());

    }
}