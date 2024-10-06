package com.zkdas.oop.service.dataFactory;

import com.zkdas.oop.model.Customer;
import net.datafaker.Faker;

import java.util.Locale;

public class CustomerFactory {
    public static Customer createCustomer() throws Exception {
        // * Создаст объект класса Customer со случайными (тестовыми данными) данными
        Faker faker = new Faker(new Locale("ru"));

        // Генерация случайного человека
        String fulname = faker.name().fullName();
        String address = faker.address().fullAddress();

        return new Customer(fulname, address);
    }
}
