package com.zkdas.oop.model.Order;

/**
 * Enum с вариантами времени доставки
 */
public enum PriorityOrderTime {
    Mode0("9:00 – 11:00"),
    Mode1("11:00 – 13:00"),
    Mode2("13:00 – 15:00"),
    Mode3("15:00 – 17:00"),
    Mode4("17:00 – 19:00"),
    Mode5("19:00 – 21:00");
    // текстовое представления опции выбранной даты
    private final String targetDate;

    /**
     * Конструктор создаст enum PriorityOrderTime
     * @param date String текстовое представления опции выбранной даты
     */
    PriorityOrderTime(String date) {
        this.targetDate = date;
    }

    /**
     * Вернет текстовое представление
     * @return String текстовое представления опции выбранной даты
     */
    public String getTargetDate() {
        return targetDate;
    }

    /**
     * Вернет текстовое представление
     * @return String текстовое представления опции выбранной даты
     */
    @Override
    public String toString() {
        return targetDate;
    }
}
