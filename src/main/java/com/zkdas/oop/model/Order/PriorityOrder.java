package com.zkdas.oop.model.Order;

import com.zkdas.oop.model.Customer.Customer;

import java.util.Date;
/**
 * Класс PriorityOrder класс модель данных для приоритетного заказа
 */
public class PriorityOrder extends Order {
    private PriorityOrderTime _desiredDeliveryTime;
    private Date _desiredDeliveryDate;

    /**
     * Конструктор PriorityOrder
     * @param customer Customer что заказал товар
     * @param desiredDeliveryDate Date дата заказа
     * @param desiredDeliveryTime PriorityOrderTime вариант доставки
     */
    public PriorityOrder(Customer customer, Date desiredDeliveryDate, PriorityOrderTime desiredDeliveryTime) {
        super(customer);

        this._desiredDeliveryTime = desiredDeliveryTime;
        this._desiredDeliveryDate = desiredDeliveryDate;
    }

    /**
     * Вернет желаемую время доставки
     * @return enum PriorityOrderTime с вариантом даты доставки
     */
    public PriorityOrderTime getDesiredDeliveryTime() {
        return _desiredDeliveryTime;
    }

    /**
     * Установка желаемого время доставки
     * @param _desiredDeliveryTime enum PriorityOrderTime с опциями вянта даты
     */
    public void setDesiredDeliveryTime(PriorityOrderTime _desiredDeliveryTime) {
        this._desiredDeliveryTime = _desiredDeliveryTime;
    }

    /**
     * Вернет желаемую дату доставки
     * @return Date желаемая дата доставки
     */
    public Date getDesiredDeliveryDate() {
        return _desiredDeliveryDate;
    }

    /**
     * Установка желаемого время доставки
     * @param _desiredDeliveryDate Date желаемую дату доставки
     */
    public void setDesiredDeliveryDate(Date _desiredDeliveryDate) {
        this._desiredDeliveryDate = _desiredDeliveryDate;
    }
}
