package com.bcp;

import java.time.*;

public class ZonedDateTimeSamples {

    public ZonedDateTimeSamples() {
    }

    public static void main(String[] args) {

        ZoneId london = ZoneId.of("Europe/London");
        ZoneId la = ZoneId.of("America/Los_Angeles");
        ZoneId lima = ZoneId.of("America/Lima");

        LocalDateTime someTime = LocalDateTime.of(2025, Month.JULY
                , 24, 4, 55);
        ZonedDateTime londonTime = ZonedDateTime.of(someTime, london);
        ZonedDateTime laTime = londonTime.withZoneSameInstant(la);
        ZonedDateTime limaTime = londonTime.withZoneSameInstant(lima);

        System.out.println("London time: " + londonTime);
        System.out.println("LA time    : " + laTime);
        System.out.println("Lima time  : " + limaTime);


        // Diferencias en horas con Lima
        long diffLondonLima = Duration.between(limaTime.toLocalDateTime(), londonTime.toLocalDateTime()).toHours();
        long diffLaLima = Duration.between(limaTime.toLocalDateTime(), laTime.toLocalDateTime()).toHours();

        System.out.println("\nDiferencia horaria con Lima:");
        System.out.println("Londres - Lima: " + diffLondonLima + " horas");
        System.out.println("Los Ángeles - Lima: " + diffLaLima + " horas");
    }
}
