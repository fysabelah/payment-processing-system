package com.customers.management.system.customersmicroservice.integration;

import com.customers.management.system.customersmicroservice.entities.Cliente;
import com.customers.management.system.customersmicroservice.entities.ClienteDocumento;
import com.customers.management.system.customersmicroservice.frameworks.db.ClienteRepository;
import com.customers.management.system.customersmicroservice.interfaceadapters.presenters.dtos.ClienteDto;
import com.customers.management.system.customersmicroservice.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;

@AutoConfigureMockMvc
@EnableWebMvc

class CustomerWebTest extends TestUtils {

    @Autowired
    private MockMvc mockMvc;

    private static final String REQUEST_MAPPING_ROOT = "/customer";

    private static final String JSON_DEFAULT_CLIENTE = "clienteTeste.json";


    @Autowired
    private ClienteRepository clienteRepository;
    @AfterEach
    void cleanUp() {
        this.clienteRepository.deleteAll();
    }
    private Cliente getCliente() throws IOException{
        File file = getFile(JSON_DEFAULT_CLIENTE);

        Cliente cliente = objectMapper.readValue(file, Cliente.class);

        return this.clienteRepository.save(cliente);
    }

    @Test
    @DisplayName("Teste para informações de um cliente")
    void insert() throws Exception{
        String bodyInsert = getMock(JSON_DEFAULT_CLIENTE, ClienteDto.class);

        this.mockMvc.perform(
                post(REQUEST_MAPPING_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyInsert)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste para não permitir duplicidade no número do documento")
    void insertDuplicate() throws Exception{
        String bodyInsert = getMock(JSON_DEFAULT_CLIENTE, ClienteDto.class);

        getCliente();

        this.mockMvc.perform(
                post(REQUEST_MAPPING_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyInsert)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste para buscar cliente por documento")
    void findCostumerByDocument() throws Exception{
        Cliente cliente = getCliente();

        for (ClienteDocumento clienteDocumento : cliente.getDocumentos()){
            this.mockMvc.perform(
                    get(REQUEST_MAPPING_ROOT + "/document/" + clienteDocumento.getDocumento())
            ).andExpect(status().isOk());
        }
    }

    @Test
    @DisplayName("Teste para desativar um cliente")
    void disableCostumer() throws Exception {
        Cliente cliente = getCliente();

        mockMvc.perform(put(REQUEST_MAPPING_ROOT + "/disable/" + cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        ).andExpect(status().isOk())
                        .andReturn().getResponse();
    }
}
