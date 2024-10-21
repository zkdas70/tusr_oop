package com.zkdas.oop.model;

import com.zkdas.oop.service.IdGenerator;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    /**
     * Класс модель данных заказа
     */
    private static final IdGenerator _idGenerator = new IdGenerator();
    private final int _id;
    private final Date _date = new Date();
    private Address _address;
    private double _prise;
    private ArrayList<Item> _items;

    public Order(Customer customer) {
        _id = _idGenerator.get_next_id();
        _address = customer.getAddress();
        _prise = 0;
    }

    public int get_id() {
        return _id;
    }

    public Date get_date() {
        return _date;
    }

    public Address get_address() {
        return _address;
    }

    public void set_address(Address address) {
        this._address = address;
    }

    public double get_prise() {
        return _prise;
    }

    public void set_prise(double prise) {
        this._prise = prise;
    }

    public ArrayList<Item> get_items() {
        return _items;
    }

    public void set_items(ArrayList<Item> items) {
        this._items = items;
    }
}
