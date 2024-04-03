package com.team1.progettocarsharingteam1.entities.enums;

public enum ChargeEnum {

    HOURLY ("hourly charge",6.0);

    private final String chargeEnum;

    ChargeEnum(String chargeEnum, double charge) {
        this.chargeEnum = chargeEnum;
    }

    public String getChargeEnum() {
        return chargeEnum;
    }
}
