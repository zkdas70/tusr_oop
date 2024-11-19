package com.zkdas.oop.model.Customer.Events;

import java.util.EventObject;

public class CustomerChanged extends EventObject {
    /**
     * Создает событие.
     *
     * @param source объект, на котором изначально произошло событие
     * @throws IllegalArgumentException, если source равен null
     */
    public CustomerChanged(Object source) {
        super(source);
    }
}
