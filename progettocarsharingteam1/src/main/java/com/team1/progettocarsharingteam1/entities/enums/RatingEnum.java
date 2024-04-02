package com.team1.progettocarsharingteam1.entities.enums;

public enum RatingEnum {
    UNO("✩"),

    DUE("✩✩"),

    TRE("✩✩✩"),

    QUATTRO("✩✩✩✩"),

    CINQUE("✩✩✩✩✩");

    private final String description;

    RatingEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
