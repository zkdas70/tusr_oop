package com.zkdas.oop.service.limitedStrinds;

import com.zkdas.oop.model.Customer.Address;
import com.zkdas.oop.model.Customer.Customer;
import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;
import com.zkdas.oop.service.dataFactory.CustomerFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloneTest {
    @Test
    void testPostIndexClone() throws Exception {
        PostIndex sample = new PostIndex("232244");
        PostIndex clone = sample.clone();

        // проверка, что клонирование прошло
        assertNotSame(sample, clone);

        assertEquals(sample, clone);

        sample.setPostIndex("000000");

        assertNotEquals(sample, clone);
    }
    @Test
    void testLimitedStingClone() throws Exception {
        LimitedSting sample = new LimitedSting(2);
        sample.setData("22");
        LimitedSting clone = sample.clone();

        // проверка, что клонирование прошло
        assertNotSame(sample, clone);

        assertEquals(sample, clone);

        sample.setData("23");

        assertNotEquals(sample, clone);
    }
    @Test
    void testLimitedFloatClone() throws Exception {
        LimitedFloat sample = new LimitedFloat(0, 4);
        sample.setValue(3);
        LimitedFloat clone = sample.clone();

        // проверка, что клонирование прошло
        assertNotSame(sample, clone);

        assertEquals(sample, clone);

        sample.setValue(2);

        assertNotEquals(sample, clone);
    }
    @Test
    void testItemClone() throws Exception {
        Item sample = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);
        Item clone = sample.clone();
        // проверка успешного клонирования
        assertNotSame(sample, clone);
        assertEquals(sample, clone);

        assertEquals(sample.getId(), clone.getId());
        assertEquals(sample.getName(), clone.getName());
        assertEquals(sample.getInfo(), clone.getInfo());
        assertEquals(sample.getCost(), clone.getCost());
        assertEquals(sample.getCategory(), clone.getCategory());
        // изменяю клон
        clone.setName("лимон данный самой жизнью");
        clone.setInfo("Взрывной лимон");
        clone.setCost(2);
        clone.setCategory(Category.NONE);
        // проверка, что поля не сыллки друг на друга
        assertNotEquals(sample.getName(), clone.getName());
        assertNotEquals(sample.getInfo(), clone.getInfo());
        assertNotEquals(sample.getCost(), clone.getCost());
        assertNotEquals(sample.getCategory(), clone.getCategory());
    }
    @Test
    void testCustomerClone() throws Exception {
        Customer sample = new Customer("Кеб Джонсон", CustomerFactory.createAddress());
        Customer clone = sample.clone();

        // проверка успешного клонирования
        assertNotSame(sample, clone);

        assertEquals(sample.getId(), clone.getId());
        assertEquals(sample.getFulname(), clone.getFulname());

        // изменяю клон
        clone.setFulname("Кеб Джонсон (умер)");

        assertNotEquals(sample.getFulname(), clone.getFulname());

        assertEquals(sample, clone);
    }
    @Test
    void testAddressClone() throws Exception {
        Address sample = new Address();
        sample.setCountry("Нигер");
        sample.setCity("Москва");
        sample.setStreet("Асения");
        sample.setBuilding("2");
        sample.setApartment("123");
        sample.setPostIndex("123456");

        Address clone = sample.clone();

        // проверка успешного клонирования
        assertNotSame(sample, clone);

        assertEquals(sample.getCountry(), clone.getCountry());
        assertEquals(sample.getCity(), clone.getCity());
        assertEquals(sample.getStreet(), clone.getStreet());
        assertEquals(sample.getBuilding(), clone.getBuilding());
        assertEquals(sample.getApartment(), clone.getApartment());
        assertEquals(sample.getPostIndex(), clone.getPostIndex());
        // изменяю клон
        clone.setCountry("Россия");
        clone.setCity("Томск");
        clone.setStreet("пер Ямской");
        clone.setBuilding("нет");
        clone.setApartment("1");
        clone.setPostIndex("654321");

        assertNotEquals(sample.getCountry(), clone.getCountry());
        assertNotEquals(sample.getCity(), clone.getCity());
        assertNotEquals(sample.getStreet(), clone.getStreet());
        assertNotEquals(sample.getBuilding(), clone.getBuilding());
        assertNotEquals(sample.getApartment(), clone.getApartment());
        assertNotEquals(sample.getPostIndex(), clone.getPostIndex());
    }
}