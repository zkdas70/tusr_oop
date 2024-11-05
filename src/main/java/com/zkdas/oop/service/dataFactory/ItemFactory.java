package com.zkdas.oop.service.dataFactory;

import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;
import net.datafaker.Faker;

import java.util.Locale;

public class ItemFactory {
    /**
     * Сгенерирует новый подходящий для тестов Item
     */
    public static Item createItem() throws Exception {
        // * Создаст объект класса Item со случайными (тестовыми данными) данными

        Faker faker = new Faker(new Locale("ru"));

        // Генерация случайного товара
        String productName = faker.commerce().productName();
        String productDescription = faker.lorem().sentence(10); // описание на латыни (бес смысла)

        float productPrice = Float.parseFloat(faker.commerce().price().replace(',', '.'));

        Category category = Category.NONE;
        // Вывод информации о товаре
        return new Item(productName, productDescription, productPrice, category);
    }
}
