package com.zkdas.oop.model;

public enum OrderStatus {
    /**
     * enum со всеми доступными в программе категориями
     */
    New("новый"),
    Processing("обработка"),
    Assembly("собран"),
    Sent("отправлен"),
    Delivered("доставлен"),
    Returned("возвращен"),
    Abandoned("заброшенный");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
