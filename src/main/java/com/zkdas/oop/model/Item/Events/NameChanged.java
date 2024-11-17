package com.zkdas.oop.model.Item.Events;

public class NameChanged extends ItemEvent {
    /**
     * Создает событие.
     *
     * @param source объект, на котором изначально произошло событие
     * @throws IllegalArgumentException, если source равен null
     */
    public NameChanged(Object source) {
        super(source);
    }
}
