package com.payment.system.customer_service.usercase;

import com.payment.system.customer_service.entities.Client;
import com.payment.system.customer_service.util.exception.ValidationsException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientBusiness {

    public void validateCreation(Optional<Client> optional, Client client) throws ValidationsException {
        if (optional.isPresent()) {
            throw new ValidationsException("0002");
        }

        validateObligatoryField(client);
    }

    private void validateObligatoryField(Client client) throws ValidationsException {
        if (client.getDocument() == null || client.getDocument().isBlank()) {
            throw new ValidationsException("0003");
        }

        if (client.getName() == null || client.getName().isBlank()) {
            throw new ValidationsException("0004");
        }

        if (client.getCellphone() == null || client.getCellphone().isBlank()) {
            throw new ValidationsException("0005");
        }

        if (client.getAddress() == null) {
            throw new ValidationsException("0006");
        }
    }

    public void update(Client client, Client newValues) throws ValidationsException {
        newValues.setDocument(client.getDocument());

        validateObligatoryField(newValues);

        client.setName(newValues.getName());
        client.setEmail(newValues.getEmail());
        client.setCellphone(newValues.getCellphone());
        client.setAddress(newValues.getAddress());
    }
}
