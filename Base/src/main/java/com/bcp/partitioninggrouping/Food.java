package com.bcp.partitioninggrouping;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends Product {
    public Food(String name, BigDecimal price, LocalDate bestBefore) {
        super(name, price, "Comida", bestBefore);
    }

    public Food(String name, BigDecimal price) {
        super(name, price, "Comida");
    }
} 