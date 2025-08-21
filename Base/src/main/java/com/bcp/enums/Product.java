package com.bcp.enums;

public class Product {

    private String name;
    private String caution;
    private Condition condition;

    public Product(String name, Condition condition) {
        this.name = name;
        this.condition = condition;
    }

    public Product serve() {
        switch (condition) {
            case COLD:
                this.addCaution("Warning COLD!");
                break;
            case WARM:
                this.addCaution("Just right");
                break;
            case HOT:
                this.addCaution("Warning HOT!");
                break;
        }
        return this;
    }

    private void addCaution(String caution) {
        this.caution = caution;
    }

    public String getCaution() {
        return caution;
    }
}
