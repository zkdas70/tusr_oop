package com.zkdas.oop.model.Customer;

import com.zkdas.oop.model.Customer.Events.AddressChanged;
import com.zkdas.oop.model.Customer.Events.CustomerChanged;
import com.zkdas.oop.model.Customer.Events.IAddressEventListener;
import com.zkdas.oop.model.Customer.Events.ICustomerEventListener;
import com.zkdas.oop.model.Order.Order;
import com.zkdas.oop.model.Order.PriorityOrder;
import com.zkdas.oop.model.Order.PriorityOrderTime;
import com.zkdas.oop.model.discounts.IDiscount;
import com.zkdas.oop.model.discounts.PointsDiscount;
import com.zkdas.oop.service.IdGenerator;
import com.zkdas.oop.service.LimitedFields.LimitedSting;
import com.zkdas.oop.service.Validators.ValueValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer implements Cloneable{
    /**
     * Класс модель данных Customer
     */
    private static final IdGenerator _idGenerator = new IdGenerator(); // генератор id принажавший не объекту, а классу
    private final int _id;// целочисленное readonly-поле, хранящее уникальный номер товара.
    protected final LimitedSting _fulname = new LimitedSting(200);//строковое поле с полным именем покупателя (Фамилия имя отчество), до 200 символов.
    private Address _address; // Address покупателя
    private final Cart _cart; // корзина покупателя
    private ArrayList<Order> _orders = new ArrayList<>(); // все заказы покупателя (в виде ссылок)
    private boolean _isPriority; // приоритетный ли покупатель
    private ObservableList<IDiscount> _discounts; // все скидки пользователя
    // список обработчиков
    private List<ICustomerEventListener> _listeners = new ArrayList<ICustomerEventListener>();

    /**
     * Подпишет обработчик на событие
     * @param listener обработчик
     */
    public void addListener(ICustomerEventListener listener) {
        _listeners.add(listener);
    }

    /**
     * Уведомит всех слушателей
     */
    private void notifyListeners() {
        for (ICustomerEventListener listener : _listeners){
            listener.processEvent(new CustomerChanged(this));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer other) {
            return other._fulname.equals(this._fulname) && other._address.equals(this._address)
                    && other._cart.equals(this._cart) && _isPriority == other._isPriority
                    && other._orders.equals(this._orders) && other._discounts.equals(this._discounts);
        }
        return false;
    }

    @Override
    public Customer clone() {
        try {
            return new Customer(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

        _discounts = FXCollections.observableArrayList();
        _discounts.add(new PointsDiscount());
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
        _discounts = FXCollections.observableArrayList(customer.get_discounts());
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

        notifyListeners();
    }

    public void setAddress(Address address) throws Exception {
        _address = new Address(address); // созданная копия это уже не агрегация, а композиция
        notifyListeners();
    }

    public Cart get_cart() {
        return _cart;
    }

    public boolean is_Priority() {
        return _isPriority;
    }

    public void set_isPriority(boolean _isPriority) {
        this._isPriority = _isPriority;
        notifyListeners();
    }

    /**
     * Создаст заказ
     * @param discount скидка
     * @return Order созданный заказ
     */
    public Order createOrder(double discount) {
        Order order;
        // выберем тип заказа (приоритетный или обычный)
        if (_isPriority) {
            order = new PriorityOrder(this, new Date(), PriorityOrderTime.Mode0);
        } else {
            order = new Order(this);
        }
        // вычитаю скидку из стоимости заказа
        order.setPrise(order.getPrise() - discount);

        _orders.add(order);

//        for (IDiscount i: _discounts) {
//            i.Update(_cart.getItems());
//        }

        return order;
    }

    /**
     * Создаст заказ (скидка равна 0)
     * @return Order созданный заказ
     */
    public Order createOrder(){
        return createOrder(0);
    }

    public ArrayList<Order> get_orders() {
        return _orders;
    }

    public void set_orders(ArrayList<Order> _orders) {
        this._orders = _orders;
        notifyListeners();
    }

    public ObservableList<IDiscount> get_discounts() {
        return _discounts;
    }
}
