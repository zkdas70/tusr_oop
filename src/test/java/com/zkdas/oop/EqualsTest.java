package com.zkdas.oop;

import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Customer.Address;
import com.zkdas.oop.model.Customer.Customer;
import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;
import com.zkdas.oop.model.Order.Order;
import com.zkdas.oop.model.Order.OrderStatus;
import com.zkdas.oop.service.dataFactory.CustomerFactory;
import com.zkdas.oop.service.dataFactory.ItemFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EqualsTest {
    @Test
    public void testItemEquals() throws Exception {
        Item i1 = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);
        Item i2 = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);

        assertEquals(i1, i2);
    }

    @Test
    public void testItemNotEquals() throws Exception {
        Item i1 = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);
        Item i2 = new Item("лимон данный самой жизнью", "Взрывной лимон", 5, Category.GROCERIES);

        assertNotEquals(i1, i2);

        i1 = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);
        i2 = new Item("лимон", "Тот самый взрывной", 5, Category.NONE);

        assertNotEquals(i1, i2);

        i1 = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);
        i2 = new Item("лимон", "Тот самый взрывной", 25, Category.GROCERIES);

        assertNotEquals(i1, i2);
    }

    @Test
    public void testAddressEquals() throws Exception {
        Address a1 = new Address("123456", "Нигер", "Москва", "Асения", "2", "123");
        Address a2 = new Address("123456", "Нигер", "Москва", "Асения", "2", "123");

        assertEquals(a1, a2);
    }

    @Test
    public void testAddressNotEquals() throws Exception {
        Address a1 = new Address("123456", "Нигер", "Москва", "Асения", "2", "123");
        Address a2 = new Address("123456", "Россия", "Москва", "Асения", "2", "123");

        assertNotEquals(a1, a2);

        a1 = new Address("654321", "Россия", "Москва", "Асения", "2", "123");
        a2 = new Address("123456", "Россия", "Москва", "Асения", "2", "123");

        assertNotEquals(a1, a2);
    }

    @Test
    public void testOrderEquals() throws Exception {
        Customer customer = new Customer("Кеб Джонсон", CustomerFactory.createAddress());
        Order o1 = new Order(customer);
        Order o2 = new Order(customer);

        assertEquals(o1, o2);
    }

    @Test
    public void testOrderNotEquals() throws Exception {
        Customer customer1 = new Customer("Кеб Джонсон", CustomerFactory.createAddress());
        Customer customer2 = new Customer("Кеб", CustomerFactory.createAddress());
        Order o1 = new Order(customer1);
        Order o2 = new Order(customer2);

        assertNotEquals(o1, o2);

        o1 = new Order(customer1);
        o2 = new Order(customer1);
        o1.setOrderStatus(OrderStatus.Abandoned);

        assertNotEquals(o1, o2);

        o1 = new Order(customer1);
        o2 = new Order(customer1);
        o1.getItems().add(new ItemForList(ItemFactory.createItem()));

        assertNotEquals(o1, o2);

        o1 = new Order(customer1);
        o2 = new Order(customer1);
        o1.setPrise(10);

        assertNotEquals(o1, o2);
    }
}
