package com.perceptioncheck.project.models;

public class Tax {

    public TaxType taxType;

    public enum TaxType {
        DEFAULT_VAT(0.20);

        private final Double value;

        TaxType(Double value) {
            this.value = value;
        }
    }

    public Tax() {
        super();
    }

    @Override
    public String toString() {
        return "Tax{" +
                "taxType=" + taxType +
                '}';
    }
}
