package com.zkdas.oop.model.Customer;

import com.zkdas.oop.model.Order.Order;
import com.zkdas.oop.model.Order.PriorityOrder;
import com.zkdas.oop.model.Order.PriorityOrderTime;
import com.zkdas.oop.service.IdGenerator;
import com.zkdas.oop.service.limitedStrinds.LimitedSting;
import com.zkdas.oop.service.Validators.ValueValidator;

import java.util.ArrayList;
import java.util.Date;

public class Customer {
    /**
     * Класс модель данных Customer
     */
    private static final IdGenerator _idGenerator = new IdGenerator(); // генератор id принажавший не объекту, а классу
    private final int _id;// целочисленное readonly-поле, хранящее уникальный номер товара.
    protected final LimitedSting _fulname = new LimitedSting(200);//строковое поле с полным именем покупателя (Фамилия имя отчество), до 200 символов.
    private Address _address; // Address покупателя
    private final Cart _cart; // корзина покупателя
    private ArrayList<Order> _orders = new ArrayList<>(); // все заказы покупателя (в виде ссылок)
    private boolean _isPriority;

    /**
     * Конструктор Customer
     * @param fulname String имя покупателя (Фамилия имя отчество), до 200 символов
     * @param address String адрес доставки для покупателя до 500 символов
     * @param isPriority boolean приоритетный ли пользователь
     */
    public Customer(String fulname, Address address, boolean isPriority) throws Exception {
        _id = _idGenerator.get_next_id();

        _fulname.setData(fulname);
        _address = address.clone(); // созданная копия это уже не агрегация, а композиция
        _cart = new Cart();
        _isPriority = isPriority;
    }

    /**
     * Конструктор Customer (создаст не приоритетного пользователя)
     * @param fulname String имя покупателя (Фамилия имя отчество), до 200 символов
     * @param address String адрес доставки для покупателя до 500 символов
     */
    public Customer(String fulname, Address address) throws Exception {
        this(fulname, address, false);
    }

    /**
     * Копирующий конструктор Customer
     * @param customer объект, что надо скопировать
     * @param copyId True скопирует id, False нет
     */
    public <T extends Customer> Customer(T customer, boolean copyId) throws Exception {
        if (copyId) {
            _id = customer.getId();
        } else {
            _id = _idGenerator.get_next_id();
        }
        setFulname(customer.getFulname());
        setAddress(customer.getAddress());
        _cart = customer.get_cart().clone();
    }

    /**
     * Копирующий конструктор Customer копирует id
     * @param customer объект, что надо скопировать
     */
    public <T extends Customer> Customer(T customer) throws Exception {
        this(customer, true);
    }

    public int getId() {
        return _id;
    }

    public String getFulname() {
        return _fulname.toString();
    }

    public Address getAddress() {
        return _address;
    }

    public void setFulname(String fulname) throws Exception {
        ValueValidator.AssertStringOnLength(fulname, _fulname.getLength(), "name");

        _fulname.setData(fulname);
    }

    public void setAddress(Address address) throws Exception {
        _address = new Address(address); // созданная копия это уже не агрегация, а композиция
    }

    public Cart get_cart() {
        return _cart;
    }

    public boolean is_Priority() {
        return _isPriority;
    }

    public void set_isPriority(boolean _isPriority) {
        this._isPriority = _isPriority;
    }

    public Order createOrder() {
        Order order;
        // выберем тип заказа (приоритетный или обычный)
        if (_isPriority) {
            order = new PriorityOrder(this, new Date(), PriorityOrderTime.Mode0);
        } else {
            order = new Order(this);
        }
        _orders.add(order);
        return order;
    }

    public ArrayList<Order> get_orders() {
        return _orders;
    }

    public void set_orders(ArrayList<Order> _orders) {
        this._orders = _orders;
    }
}
