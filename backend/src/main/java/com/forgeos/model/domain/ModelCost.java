package com.forgeos.model.domain;

import java.math.BigDecimal;

public class ModelCost {
    private BigDecimal inputCost;
    private BigDecimal outputCost;
    private BigDecimal totalCost;
    private String currency;
    private String pricingVersion;

    public ModelCost(BigDecimal inputCost, BigDecimal outputCost, String currency, String pricingVersion) {
        this.inputCost = inputCost;
        this.outputCost = outputCost;
        this.totalCost = inputCost.add(outputCost);
        this.currency = currency;
        this.pricingVersion = pricingVersion;
    }

    public BigDecimal getTotalCost() { return totalCost; }
    public String getCurrency() { return currency; }
}
