package com.bcp.herencia;

import java.math.BigDecimal;

public class Drink extends Product{

    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
