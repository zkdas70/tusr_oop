package com.zkdas.oop.model;

import com.zkdas.oop.controller.modelForController.CustomerForList;
import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Order.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Модель Store класс содержащий данные программы в статических полях
 * (я считаю что они должны быть в единственном экземпляре)
 */
public class Store {
    static private ObservableList<ItemForList> _items;
    static private ObservableList<CustomerForList> _customers;
    static private ObservableList<Order> _orders;

    public Store() {
        // если переменные не инициализированы, то инициализируем
        if (_items == null) {
            _items = FXCollections.observableArrayList();
        }
        if (_customers == null) {
            _customers = FXCollections.observableArrayList();
        }

        if (_orders == null) {
            _orders = FXCollections.observableArrayList();
        }
    }

    /**
     * Вернет ссылку на глобальный список Item
     */
    public ObservableList<ItemForList> getItems() {
        return _items; // ObservableList ссылочный тип данных - вернет ссылку на _items
    }
    /**
     * Вернет ссылку на глобальный список Customer
     */
    public ObservableList<CustomerForList> getCustomers() {
        return _customers; // ObservableList ссылочный тип данных - вернет ссылку на _customers
    }
    /**
     * Вернет ссылку на глобальный список Order
     */
    public ObservableList<Order> getOrders() {
        return _orders;
    }
}
