package com.bcp.partitioninggrouping;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Drink extends Product {
    public Drink(String name, BigDecimal price, LocalDate bestBefore) {
        super(name, price, "Bebida", bestBefore);
    }

    public Drink(String name, BigDecimal price) {
        super(name, price, "Bebida");
    }
} 