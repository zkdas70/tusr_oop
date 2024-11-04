package com.zkdas.oop.model;

public enum PriorityOrderTime {
    /**
     * Enum с вариантами времени доставки
     */
    Mode0("9:00 – 11:00"),
    Mode1("11:00 – 13:00"),
    Mode2("13:00 – 15:00"),
    Mode3("15:00 – 17:00"),
    Mode4("17:00 – 19:00"),
    Mode5("19:00 – 21:00");

    private final String targetDate;

    PriorityOrderTime(String date) {
        this.targetDate = date;
    }

    public String getTargetDate() {
        return targetDate;
    }

    @Override
    public String toString() {
        return targetDate;
    }
}
