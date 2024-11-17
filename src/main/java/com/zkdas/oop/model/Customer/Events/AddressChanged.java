package com.zkdas.oop.model.Customer.Events;

import java.util.EventObject;

public class AddressChanged extends EventObject {
    private final String _filed;

    /**
     * Создает событие.
     *
     * @param source объект, на котором изначально произошло событие
     * @param filed поле, что изменилось
     * @throws IllegalArgumentException, если source равен null
     */
    public AddressChanged(Object source, String filed) {
        super(source);
        this._filed = filed;
    }

    public String get_filed_name(){
        return _filed;
    }
}
