package com.customers.management.system.customersmicroservice.interfaceadapters.usercase;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import com.customers.management.system.customersmicroservice.util.exception.ValidationsException;
import org.springframework.stereotype.Component;

@Component
public class ClienteBusiness {

    public void create(Cliente clienteDocumentoDuplicado) throws ValidationsException{
        if (clienteDocumentoDuplicado != null) {
            throw new ValidationsException("0100");
        }
    }


}
