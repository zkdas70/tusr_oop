package com.zkdas.oop.model;

import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.service.IdGenerator;
import javafx.collections.ObservableList;

import java.util.Date;

public class Order {
    /**
     * Класс модель данных заказа
     */
    private static final IdGenerator _idGenerator = new IdGenerator();
    private final int _id;
    private final Date _date = new Date(); // создаст текущую дату
    private Address _address;
    private String _name;
    private double _prise;
    private ObservableList<ItemForList> _items;
    private OrderStatus _orderStatus;

    public Order(Customer customer) {
        _id = _idGenerator.get_next_id();
        _address = customer.getAddress();
        _name = customer.getFulname();
        _items = customer.get_cart().getItems();
        _prise = customer.get_cart().getAmount();
        _orderStatus = OrderStatus.New;
    }

    public int getId() {
        return _id;
    }

    public Date getDate() {
        return _date;
    }

    public Address getAddress() {
        return _address;
    }

    public void setAddress(Address address) {
        this._address = address;
    }

    public double getPrise() {
        return _prise;
    }

    public void setPrise(double prise) {
        this._prise = prise;
    }

    public ObservableList<ItemForList> getItems() {
        return _items;
    }

    public void setItems(ObservableList<ItemForList> items) {
        this._items = items;
    }

    public OrderStatus getOrderStatus() {
        return _orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this._orderStatus = orderStatus;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
}
