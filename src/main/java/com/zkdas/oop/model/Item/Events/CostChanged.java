package com.zkdas.oop.model.Item.Events;

public class CostChanged extends ItemEvent{
    /**
     * Создает событие.
     *
     * @param source объект, на котором изначально произошло событие
     * @throws IllegalArgumentException, если source равен null
     */
    public CostChanged(Object source) {
        super(source);
    }
}
