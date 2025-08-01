package com.bcp.constructores;

import com.bcp.enums.Condition;

public class Food extends Product{

    public Food(String name) {
        super(name);

    }

    public Food(String name, Condition condition) {
        super(name);
    }
}
