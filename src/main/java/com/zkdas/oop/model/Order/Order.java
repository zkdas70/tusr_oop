package com.zkdas.oop.model.Order;

import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Customer.Address;
import com.zkdas.oop.model.Customer.Customer;
import com.zkdas.oop.service.IdGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
/**
 * Класс модель данных заказа
 */
public class Order {
    private static final IdGenerator _idGenerator = new IdGenerator();
    private final int _id;
    private final Date _date = new Date(); // создаст текущую дату
    private Address _address; // адрес доставки (агрегируется может лучше компазировать?)
    private String _name; // имя покупателя
    private double _prise; // цена
    private ObservableList<ItemForList> _items; // веши что заказали
    private OrderStatus _orderStatus;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order other) {
            // наверное _date нельзя сравнивать (не будет смысла)
            return other._address.equals(this._address) && other._name.equals(this._name)
                    && other._prise == this._prise && other._items.equals(this._items)
                    && other._orderStatus == this._orderStatus;
        }
        return false;
    }

    /**
     * Создаст заказ (Order)
     * @param customer что сделал заказ
     */
    public Order(Customer customer) {
        _id = _idGenerator.get_next_id();

        _address = customer.getAddress();
        _name = customer.getFulname();
        _items = FXCollections.observableArrayList(customer.get_cart().getItems());
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
