package com.zkdas.oop.controller.modelForController;

import com.zkdas.oop.model.Address;
import com.zkdas.oop.model.Customer;

public class CustomerForList extends Customer {
    /**
     * Класс Customer для отображения в списке
     */
    public CustomerForList(String fulname, Address address) throws Exception {
        super(fulname, address);
    }

    public <T extends Customer> CustomerForList(T customer) throws Exception {
        this(customer.getFulname(), customer.getAddress());
    }

    @Override
    public String toString() {
        return _fulname.toString(); // Будет отображаться в ListView
    }
}
