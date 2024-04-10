package com.team1.progettocarsharingteam1.entities.enums;

public enum ChargeEnum {

    SHORT (0.5, 0L, 60L),
    MEDIUM(0.4, 61L, 240L),
    LONG(0.2, null, null);

    private final Double chargeEnum;
    private final Long min;
    private final Long max;

    ChargeEnum(Double chargeEnum, Long min, Long max) {
        this.chargeEnum = chargeEnum;
        this.min = min;
        this.max = max;
    }

    public Double getChargeEnum() {
        return chargeEnum;
    }

    public Long getMin() {
        return min;
    }

    public Long getMax() {
        return max;
    }
}
