package com.zkdas.oop.service.limitedStrinds;

public class LimitedInt {
    private final int min;
    private final int max;
    private int value;

    public LimitedInt(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void setValue(int value) {
        if (value > min && value < max) {
            this.value = value;
        }
    }
    public int getValue() {
        return value;
    }
    
}
