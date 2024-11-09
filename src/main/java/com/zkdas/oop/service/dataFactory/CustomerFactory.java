package com.zkdas.oop.service.dataFactory;

import com.zkdas.oop.model.Customer.Address;
import com.zkdas.oop.model.Customer.Customer;
import net.datafaker.Faker;

import java.util.Locale;

public class CustomerFactory {
    public static Address createAddress() throws Exception {
        Faker faker = new Faker(new Locale("ru"));
        // генерация адреса
        Address address = new Address();

        address.setPostIndex(faker.address().postcode());
        address.setCountry(faker.address().country());
        address.setCity(faker.address().city());
        address.setStreet(faker.address().streetName());
        address.setBuilding(faker.address().buildingNumber());
        address.setApartment(faker.address().streetAddressNumber());
        return address;
    }

    /**
     * Сгенерирует новый подходящего для тестов Customer
     */
    public static Customer createCustomer() throws Exception {
        // * Создаст объект класса Customer со случайными (тестовыми данными) данными
        Faker faker = new Faker(new Locale("ru"));

        // Генерация случайного человека
        String fulname = faker.name().fullName();

        return new Customer(fulname, createAddress());
    }
}
