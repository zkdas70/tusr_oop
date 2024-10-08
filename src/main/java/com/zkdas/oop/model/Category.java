package com.zkdas.oop.model;

public enum Category {
    NONE("нет"),
    ELECTRONICS("электроника"),
    CLOTHING("одежда"),
    GROCERIES("продукты"),
    FURNITURE("мебель"),
    TOYS("игрушки"),
    BOOKS("книги"),
    BEAUTY_PRODUCTS("косметика");
    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
