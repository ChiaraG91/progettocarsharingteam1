package com.team1.progettocarsharingteam1.entities.enums;

public enum CityEnum {
    ROME("Rome", "Via xxx 1"),
    MILAN("Milan", "Via xxx 1"),
    NAPLES("Naples", "Via xxx 1"),
    FLORENCE("Florence", "Via xxx 1");

    private final String nome;
    private final String indirizzo;

    CityEnum(String nome, String indirizzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }
}
