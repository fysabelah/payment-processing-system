package com.customers.management.system.customersmicroservice.interfaceadapters.usercase;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import com.customers.management.system.customersmicroservice.entities.ClienteDocumento;
import com.customers.management.system.customersmicroservice.util.exception.ValidationsException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteBusiness {

    public void create(Cliente cliente, ClienteDocumento clienteDocumento) throws ValidationsException{
        // Lógica para não duplicar o documento para algum cliente
        if (clienteDocumento != null && clienteDocumento.getId() != null){

            if ( clienteDocumento.getDocumento() != null && clienteDocumento.getDocumento().isEmpty() ){
                throw new ValidationsException("0002");
            }

            throw new ValidationsException("0100");
        }
    }

    public void desativar(Cliente clienteDesativar) throws ValidationsException{

        if (clienteDesativar != null && clienteDesativar.getId()!=null){
            if( ! clienteDesativar.isAtivo() ) {
                throw new ValidationsException("0101");
            }
        }
    }

    public void ativar(Cliente clienteAtivar) throws ValidationsException{
        if (clienteAtivar != null && clienteAtivar.getId()!=null){
            if( clienteAtivar.isAtivo() ) {
                throw new ValidationsException("0102");
            }
        }

    }

    public void validateCreation(List<Cliente> clientesDocumentos) throws ValidationsException {
        if (clientesDocumentos != null && !clientesDocumentos.isEmpty()) {
            throw new ValidationsException("0103");
        }
    }
}
