package com.bcp;

import java.util.Locale;

public class LocaleSample {

    public static void main(String[] args) {
        Locale uk = Locale.of("en", "GB");              // English, Britain
        Locale th = Locale.of("th", "TH", "TH");        // Thai, Thailand (Thai digits variant)
        Locale us = Locale.of("en", "US");              // English, America
        Locale fr = Locale.of("fr", "FR");              // French, France
        Locale cf = Locale.of("fr", "CA");              // French, Canada
        Locale frCaribbean = Locale.of("fr", "029");    // French, Caribbean
        Locale es = Locale.of("fr");                    // French

        Locale current = Locale.getDefault();           // Current default locale

        // Example: Locale with Thai digits and Buddhist calendar
        Locale thaiBuddhist = Locale.forLanguageTag("th-TH-u-ca-buddhist-nu-thai");

        // Print examples
        System.out.println("UK Locale: " + uk);
        System.out.println("Thai Locale: " + th);
        System.out.println("US Locale: " + us);
        System.out.println("French (France): " + fr);
        System.out.println("French (Canada): " + cf);
        System.out.println("French (Caribbean): " + frCaribbean);
        System.out.println("Current default: " + current);
        System.out.println("Thai Buddhist Locale: " + thaiBuddhist);
    }
}
