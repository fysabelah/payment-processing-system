package com.customers.management.system.customersmicroservice.util.enums;

public enum TipoDocumentoCliente {
    CPF("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}"),
    CNPJ("[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}"),
    RNE("");

    private final String regexFormat;

    TipoDocumentoCliente(String regexFormat) {

        this.regexFormat = regexFormat;

    }

    public String getRegexFormat() {

        return this.regexFormat;

    }

}
