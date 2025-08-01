package com.bcp;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class JavaTimeFormat {

    public static void main(String[] args) {
        // Primer ejemplo: formato con patrón explícito y locale en inglés británico
        LocalDate date = LocalDate.of(2019, Month.APRIL, 1);
        Locale locale = Locale.of("en", "GB");
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("EEEE dd MMM yyyy", locale);
        String result = date.format(dateTimeFormat);
        System.out.println(result);  // Output: Monday 01 Apr 2019

        // Segundo ejemplo: parseo con formato localizado al ruso
        date = LocalDate.parse("Tuesday 31 Mar 2020", DateTimeFormatter.ofPattern("EEEE dd MMM yyyy", Locale.ENGLISH));
        locale = Locale.of("ch");
        dateTimeFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(locale);
        result = date.format(dateTimeFormat);
        System.out.println(result);  // Output: 31 мар. 2020
    }
}
