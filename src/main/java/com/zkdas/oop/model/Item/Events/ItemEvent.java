package com.zkdas.oop.model.Item.Events;

import java.util.EventObject;

public class ItemEvent extends EventObject {
    /**
     * Создает событие.
     * @param source объект, на котором изначально произошло событие
     * @throws IllegalArgumentException, если source равен null
     */
    public ItemEvent(Object source) {
        super(source);
    }
}
