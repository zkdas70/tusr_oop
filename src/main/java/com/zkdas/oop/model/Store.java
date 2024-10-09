package com.zkdas.oop.model;

import com.zkdas.oop.controller.modelForController.CustomerForList;
import com.zkdas.oop.controller.modelForController.ItemForList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store {
    static private ObservableList<ItemForList> _items;
    static private ObservableList<CustomerForList> _customers;

    public Store() {
        if (_items == null) {
            _items = FXCollections.observableArrayList();
        }
        if (_customers == null) {
            _customers = FXCollections.observableArrayList();
        }
    }

    public ObservableList<ItemForList> getItems() {
        return _items;
    }
    public ObservableList<CustomerForList> getCustomers() {
        return _customers;
    }
}
