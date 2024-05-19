package com.customers.management.system.customersmicroservice.unit;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import com.customers.management.system.customersmicroservice.entities.ClienteDocumento;
import com.customers.management.system.customersmicroservice.interfaceadapters.usercase.ClienteBusiness;
import com.customers.management.system.customersmicroservice.util.MessageUtil;
import com.customers.management.system.customersmicroservice.util.exception.ValidationsException;
import com.customers.management.system.customersmicroservice.utils.TestUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteBusinessTest extends TestUtils {
    @Resource
    private ClienteBusiness clienteBusiness;

    @Test
    void testValidateClienteExists() {
        ValidationsException exception = assertThrows(ValidationsException.class,
                () -> this.clienteBusiness.create(new Cliente(), new ClienteDocumento()));

        assertEquals(MessageUtil.getMessage("0100"), exception.getMessage());
    }

    @Test
    void testValidateClienteNotExists(){
        assertDoesNotThrow(()-> this.clienteBusiness.create(null,null));
    }
}
