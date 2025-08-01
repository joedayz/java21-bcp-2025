package com.bcp.interfaces;

import java.time.Period;

public interface Perishable {

    //constantes
    public static final Period MAX_PERIOD = Period.ofDays(5);

    //metodos abstractos
    void perish();
    boolean isPerished();

    // Método default con lógica reutilizable
    public default boolean verifyPeriod(Period p) {
        return comparePeriod(p) < 0;
    }

    // Método privado (desde Java 9)
    private int comparePeriod(Period p) {
        return p.getDays() - MAX_PERIOD.getDays();
    }

    // Método estático accesible desde la interfaz sin una instancia
    public static int getMaxPeriodDays() {
        return MAX_PERIOD.getDays();
    }

}
