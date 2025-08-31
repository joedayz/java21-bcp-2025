package com.bcp.instanceofsample;

import java.time.LocalDate;

public class Food extends Product {

    private LocalDate bestBefore;
    public LocalDate getBestBefore() {
        return bestBefore;
    }

    @Override
    public String toString() {
        return super.toString() + "Food [bestBefore=" + bestBefore + "]";
    }
}
