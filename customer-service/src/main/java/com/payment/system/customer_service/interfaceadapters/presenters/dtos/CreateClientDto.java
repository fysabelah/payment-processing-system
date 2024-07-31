package com.payment.system.customer_service.interfaceadapters.presenters.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateClientDto {

    @NotEmpty
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?: [A-Za-zÀ-ÖØ-öø-ÿ]+)+$")
    @JsonProperty(value = "nome")
    @Schema(example = "Maria José")
    private String name;

    @NotEmpty
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}")
    @JsonProperty(value = "cpf")
    private String document;

    @NotEmpty
    @Email
    @Schema(example = "meunome@provedormail.com")
    private String email;

    @NotEmpty
    @Pattern(regexp = "\\+[0-9]{2} [0-9]{2} 9 [0-9]{4}-[0-9]{4}")
    @Schema(example = "+99 64 9 8598-7856")
    @JsonProperty(value = "telefone")
    private String cellphone;

    @NotEmpty
    @Schema(example = "Paulista")
    @JsonProperty(value = "rua")
    private String street;

    @JsonAlias(value = "cidade")
    @NotEmpty
    @Schema(example = "São Paulo")
    private String city;

    @JsonProperty(value = "estado")
    @NotEmpty
    @Schema(example = "São Paulo")
    private String state;

    @JsonProperty(value = "pais")
    @NotEmpty
    @Schema(example = "Brasil")
    private String country;

    @JsonProperty(value = "cep")
    @NotEmpty
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
    @Schema(example = "12345-678")
    private String zipCode;
}