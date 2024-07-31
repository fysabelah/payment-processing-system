package com.payment.system.customer_service.interfaceadapters.presenters.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDto extends Dto {

    @NotEmpty
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?: [A-Za-zÀ-ÖØ-öø-ÿ]+)+$")
    @Schema(example = "Maria José")
    private String name;

    @NotEmpty
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String document;

    @NotEmpty
    @Email
    @Schema(example = "meunome@provedormail.com")
    private String email;

    @NotEmpty
    @Pattern(regexp = "\\+[0-9]{2} [0-9]{2} 9 [0-9]{4}-[0-9]{4}")
    @Schema(example = "+99 64 9 8598-7856")
    @JsonAlias(value = "telefone")
    private String cellphone;

    @NotNull
    private AddressDto address;

    @Builder
    public ClientDto(Integer id, String name, String document, String email, String cellphone, AddressDto address) {
        super(id);
        this.name = name;
        this.document = document;
        this.email = email;
        this.cellphone = cellphone;
        this.address = address;
    }
}
