package com.bcp;

import java.time.*;

public class DateTimeSamples {

    public static void main(String[] args) {
//        LocalDateTime current = LocalDateTime.now();
//        LocalDateTime different = current
//                .withMinute(14)
//                .withDayOfMonth(3)
//                .plusHours(12);
//
//        int year = current.getYear();
//        boolean before = current.isBefore(different);
//
//        System.out.println("Current: " + current);
//        System.out.println("Different: " + different);
//        System.out.println("Year: " + year);
//        System.out.println("Is current before different? " + before);

        LocalDate today = LocalDate.now();
        LocalDate foolsDay = LocalDate.of(2019, Month.APRIL, 1);

        Instant timeStamp = Instant.now();
        int nanoSecondsFromLastSecond = timeStamp.getNano();

        Period howLong = Period.between(foolsDay, today);

        Duration twoHours = Duration.ofHours(2);
        long seconds = twoHours.minusMinutes(15).getSeconds();

        int days = howLong.getDays();

        System.out.println("Today: " + today);
        System.out.println("Fools' Day: " + foolsDay);
        System.out.println("Nanoseconds part of current second: " + nanoSecondsFromLastSecond);
        System.out.println("Period between Fools' Day and Today: " + howLong);
        System.out.println("Days from Period: " + days);
        System.out.println("Seconds in 1h 45m: " + seconds);



    }
}
