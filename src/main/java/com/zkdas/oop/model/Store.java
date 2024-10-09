package com.zkdas.oop.model;

import com.zkdas.oop.controller.modelForController.CustomerForList;
import com.zkdas.oop.controller.modelForController.ItemForList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store {
    /**
     * Модель Store класс содержащий списки
     */
    static private ObservableList<ItemForList> _items;
    static private ObservableList<CustomerForList> _customers;

    public Store() {
        // если переменные не инициализированы, то инициализируем
        if (_items == null) {
            _items = FXCollections.observableArrayList();
        }
        if (_customers == null) {
            _customers = FXCollections.observableArrayList();
        }
    }

    public ObservableList<ItemForList> getItems() {
        return _items; // ObservableList ссылочный тип данных - вернет ссылку на _items
    }
    public ObservableList<CustomerForList> getCustomers() {
        return _customers; // ObservableList ссылочный тип данных - вернет ссылку на _customers
    }
}
