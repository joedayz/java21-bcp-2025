package com.bcp.enums;

import static com.bcp.enums.Condition.HOT;

public class Shop {

    public static void main(String[] args) {
        Product tea = new Product("Tea", HOT);
        Person joe = new Person("Joe");
        joe.consume(tea.serve());
    }
}
