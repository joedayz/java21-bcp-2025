package com.bcp.herencia;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends Product {

    private LocalDate bestBefore;
    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public BigDecimal getPrice() {
        return super.getPrice().multiply(BigDecimal.valueOf(1.10));
    }

    @Override
    public String toString() {
        return super.toString() + " Food [bestBefore=" + bestBefore + "]";
    }
}
