package com.zkdas.oop.model.Order;

/**
 * Enum со всеми доступными в программе категориями
 */
public enum OrderStatus {
    New("новый"),
    Processing("обработка"),
    Assembly("собран"),
    Sent("отправлен"),
    Delivered("доставлен"),
    Returned("возвращен"),
    Abandoned("заброшенный");
    // строка со статусом товара
    private final String displayName;

    /**
     * Конструктор OrderStatus
     * @param displayName строка со статусом товара
     */
    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
