package com.bcp.lambda;

public class TextFilter {
    public static boolean removeA(String s) {
        return s.equals("remove A");
    }

    public int sortText(String s1, String s2) {
        return s1.compareTo(s2);
    }
} 