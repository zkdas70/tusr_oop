package com.zkdas.oop.controller.modelForController;

import com.zkdas.oop.model.Customer.Address;
import com.zkdas.oop.model.Customer.Customer;

public class CustomerForList extends Customer {
    /**
     * Класс Customer для отображения в списке
     */
    public CustomerForList(String fulname, Address address, boolean isPriority) throws Exception {
        super(fulname, address, isPriority);
    }

    public <T extends Customer> CustomerForList(T customer, boolean copyId) throws Exception {
        super(customer, copyId);
    }

    public <T extends Customer> CustomerForList(T customer) throws Exception {
        super(customer);
    }

    @Override
    public String toString() {
        return _fulname.toString(); // Будет отображаться в ListView
    }
}
