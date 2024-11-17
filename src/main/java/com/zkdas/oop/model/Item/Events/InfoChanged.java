package com.zkdas.oop.model.Item.Events;

public class InfoChanged extends ItemEvent{
    /**
     * Создает событие.
     *
     * @param source объект, на котором изначально произошло событие
     * @throws IllegalArgumentException, если source равен null
     */
    public InfoChanged(Object source) {
        super(source);
    }
}
