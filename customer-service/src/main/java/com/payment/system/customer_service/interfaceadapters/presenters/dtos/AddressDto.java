package com.payment.system.customer_service.interfaceadapters.presenters.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto extends Dto {

    @JsonAlias(value = "rua")
    @NotEmpty
    @Schema(example = "Paulista")
    private String street;

    @JsonAlias(value = "cidade")
    @NotEmpty
    @Schema(example = "São Paulo")
    private String city;

    @JsonAlias(value = "estado")
    @NotEmpty
    @Schema(example = "São Paulo")
    private String state;

    @JsonAlias(value = "pais")
    @NotEmpty
    @Schema(example = "Brasil")
    private String country;

    @JsonAlias(value = "cep")
    @NotEmpty
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
    @Schema(example = "12345-678")
    private String zipCode;

    @Builder
    public AddressDto(Integer id, String street, String city, String state, String country, String zipCode) {
        super(id);
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }
}
