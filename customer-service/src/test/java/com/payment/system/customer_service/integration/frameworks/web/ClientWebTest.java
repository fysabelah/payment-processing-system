package com.payment.system.customer_service.integration.frameworks.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.payment.system.customer_service.TestUtils;
import com.payment.system.customer_service.entities.Client;
import com.payment.system.customer_service.frameworks.db.AddressRepository;
import com.payment.system.customer_service.frameworks.db.ClientRepository;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.ClientDto;
import com.payment.system.customer_service.interfaceadapters.presenters.dtos.CreateClientDto;
import com.payment.system.customer_service.util.pagination.PagedResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@EnableWebMvc
@TestPropertySource(locations = "classpath:application.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClientWebTest extends TestUtils {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    private static final String MOCK_PATH = "src/test/resources/mocks";

    private static final String REQUEST_PATH = "/cliente";

    @AfterEach
    void cleanUp() {
        repository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    @DisplayName("Buscar todos resultado paginado")
    void testFindAll() throws Exception {
        List<Client> clientsToInsert = objectMapper.readValue(super.getMock(MOCK_PATH + "/findAllInsert.json"),
                new TypeReference<List<Client>>() {
                });

        repository.saveAll(clientsToInsert);

        MockHttpServletResponse response = mockMvc.perform(
                        get(REQUEST_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("pageSize", "10")
                                .param("initialPage", "0")
                ).andExpect(status().isOk())
                .andReturn().getResponse();

        PagedResponse<ClientDto> content = objectMapper.readValue(
                response.getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<PagedResponse<ClientDto>>() {
                }
        );

        PagedResponse<ClientDto> expected = objectMapper.readValue(
                super.getMock(MOCK_PATH + "/findAllResult.json"),
                new TypeReference<PagedResponse<ClientDto>>() {
                }
        );

        assertEquals(expected.getPage().getPage(), content.getPage().getPage());
        assertEquals(expected.getPage().getPageSize(), content.getPage().getPageSize());
        assertEquals(expected.getPage().getTotalPages(), content.getPage().getTotalPages());
        assertEquals(expected.getData().size(), content.getData().size());
        super.assertJsonEquals(
                super.objectMapper.writeValueAsString(expected.getData()),
                super.objectMapper.writeValueAsString(content.getData())
        );
    }

    @Test
    @DisplayName("Buscar por documento")
    void findByDocument() throws Exception {
        List<Client> clientsToInsert = objectMapper.readValue(super.getMock(MOCK_PATH + "/findAllInsert.json"),
                new TypeReference<List<Client>>() {
                });

        Client toInsert = clientsToInsert.get(0);
        toInsert.setId(1);
        toInsert.getAddress().setId(1);

        repository.save(toInsert);

        MockHttpServletResponse response = mockMvc.perform(
                        get(REQUEST_PATH + "/document/" + toInsert.getDocument())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn().getResponse();

        super.assertJsonEquals(
                super.objectMapper.writeValueAsString(toInsert),
                response.getContentAsString(StandardCharsets.UTF_8)
        );
    }

    @Test
    @DisplayName("Buscar por id")
    void findById() throws Exception {
        List<Client> clientsToInsert = objectMapper.readValue(super.getMock(MOCK_PATH + "/findAllInsert.json"),
                new TypeReference<List<Client>>() {
                });

        Client toInsert = clientsToInsert.get(0);
        toInsert.setId(1);
        toInsert.getAddress().setId(1);

        repository.save(toInsert);

        MockHttpServletResponse response = mockMvc.perform(
                        get(REQUEST_PATH + "/" + toInsert.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn().getResponse();

        super.assertJsonEquals(
                super.objectMapper.writeValueAsString(toInsert),
                response.getContentAsString(StandardCharsets.UTF_8)
        );
    }

    @Test
    @DisplayName("Buscar por id não encontrado")
    void findByIdNotFound() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get(REQUEST_PATH + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andReturn().getResponse();

        JsonNode content = objectMapper.readTree(response.getContentAsString(StandardCharsets.UTF_8));

        assertEquals("Não encontrado", content.get("error").asText());
        assertEquals("Cliente não encontrado.", content.get("message").asText());
    }

    @Test
    @DisplayName("Buscar por documento não encontrado")
    void findByDocumentNotFound() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        get(REQUEST_PATH + "/document/000.000.000-59")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andReturn().getResponse();

        JsonNode content = objectMapper.readTree(response.getContentAsString(StandardCharsets.UTF_8));

        assertEquals("Não encontrado", content.get("error").asText());
        assertEquals("Cliente não encontrado.", content.get("message").asText());
    }

    @Test
    @DisplayName("Inserir client")
    void insert() throws Exception {
        CreateClientDto body = super.objectMapper.readValue(
                super.getMock(MOCK_PATH + "/create.json"),
                CreateClientDto.class
        );

        MockHttpServletResponse response = mockMvc.perform(
                        post(REQUEST_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(super.objectMapper.writeValueAsString(body))
                ).andExpect(status().isOk())
                .andReturn().getResponse();

        super.assertJsonEquals("1", response.getHeader("id_cliente"));
    }

    @Test
    @DisplayName("Inserir erro de payload")
    void insertPayloadError() throws Exception {
        CreateClientDto body = super.objectMapper.readValue(
                super.getMock(MOCK_PATH + "/create.json"),
                CreateClientDto.class
        );

        body.setName("Isa");

        MockHttpServletResponse response = mockMvc.perform(
                        post(REQUEST_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(super.objectMapper.writeValueAsString(body))
                ).andExpect(status().isUnprocessableEntity())
                .andReturn().getResponse();

        JsonNode content = objectMapper.readTree(response.getContentAsString(StandardCharsets.UTF_8));

        assertEquals("Possível erro de payload", content.get("error").asText());
        assertEquals("name: must match \"^[A-Za-zÀ-ÖØ-öø-ÿ]+(?: [A-Za-zÀ-ÖØ-öø-ÿ]+)+$\"", content.get("message").asText());
    }

    @Test
    @DisplayName("Inserir documento duplicado")
    void insertDuplicateClient() throws Exception {
        Client toInsert = objectMapper.readValue(super.getMock(MOCK_PATH + "/findAllInsert.json"),
                new TypeReference<List<Client>>() {
                }).get(0);

        repository.save(toInsert);

        CreateClientDto body = super.objectMapper.readValue(
                super.getMock(MOCK_PATH + "/create.json"),
                CreateClientDto.class
        );

        body.setDocument(toInsert.getDocument());

        MockHttpServletResponse response = mockMvc.perform(
                        post(REQUEST_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(super.objectMapper.writeValueAsString(body))
                ).andExpect(status().isBadRequest())
                .andReturn().getResponse();

        JsonNode content = objectMapper.readTree(response.getContentAsString(StandardCharsets.UTF_8));

        assertEquals("Bloqueado por regra de negócio", content.get("error").asText());
        assertEquals("O documento informado já encontra-se cadastrado.", content.get("message").asText());
    }

    @Test
    @DisplayName("Atualizar cliente")
    void update() throws Exception {
        Client toUpdate = objectMapper.readValue(super.getMock(MOCK_PATH + "/findAllInsert.json"),
                new TypeReference<List<Client>>() {
                }).get(0);

        repository.save(toUpdate);

        toUpdate.setName("Isabela Teste");
        toUpdate.setEmail("novo@gmail.com");
        toUpdate.setCellphone("+55 62 9 9632-8547");

        toUpdate.getAddress().setCity("Nova Cidade");

        MockHttpServletResponse response = mockMvc.perform(
                        put(REQUEST_PATH + "/" + toUpdate.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(super.objectMapper.writeValueAsString(toUpdate))
                ).andExpect(status().isOk())
                .andReturn().getResponse();

        super.assertJsonEquals(
                super.objectMapper.writeValueAsString(toUpdate),
                response.getContentAsString(StandardCharsets.UTF_8)
        );
    }
}