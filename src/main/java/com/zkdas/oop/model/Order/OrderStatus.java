package com.zkdas.oop.model.Order;

public enum OrderStatus {
    /**
     * Enum со всеми доступными в программе категориями
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