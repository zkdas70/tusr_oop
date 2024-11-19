package com.zkdas.oop.model.Customer.Events;

public interface ICustomerEventListener {
    /**
     * Метод для обработки события
     * @param event событие
     */
    void processEvent(CustomerChanged event);
}
