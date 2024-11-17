package com.zkdas.oop.model.Customer.Events;

import com.zkdas.oop.model.Item.Events.ItemEvent;

public interface IAddressEventListener {
    /**
     * Метод для обработки события
     * @param event событие
     */
    void processEvent(AddressChanged event);
}
