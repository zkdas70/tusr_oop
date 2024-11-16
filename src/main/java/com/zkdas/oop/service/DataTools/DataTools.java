package com.zkdas.oop.service.DataTools;

import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;

import java.util.List;

public class DataTools {
    /**
     * Удалит все данные, что не проходят фильтр
     * !ВАЖНО все данные, что не проходят фильтр, будут удалены и из изначального списка
     * @param items список элементов
     * @param lowPrice нижний порог
     * @return Т с удаленными данными
     * @param <I> наследник Item
     * @param <T> любой список I
     */
    public static <I extends Item, T extends List<I>> T price_filter(T items, double lowPrice){
        items.removeIf(item -> item.getCost() < lowPrice);
        return items;
    }
    /**
     * Удалит все данные, что не проходят фильтр (5_000 по умолчанию)
     * !ВАЖНО все данные, что не проходят фильтр, будут удалены и из изначального списка
     * @param items список элементов
     * @return Т с удаленными данными
     * @param <I> наследник Item
     * @param <T> любой список I
     */
    public static <I extends Item, T extends List<I>> T price_filter(T items){
        return price_filter(items, 5_000);
    }

    /**
     * Удалит все данные, что не проходят фильтр
     * !ВАЖНО все данные, что не проходят фильтр, будут удалены и из изначального списка
     * @param items список элементов
     * @param filter категория товара, что надо отфильтровать
     * @return Т с удаленными данными
     * @param <I> наследник Item
     * @param <T> любой список I
     */
    public static <I extends Item, T extends List<I>> T category_filter(T items, Category filter){
        items.removeIf(item -> item.getCategory() != filter);
        return items;
    }

    /**
     * Удалит все данные, что не проходят фильтр
     * !ВАЖНО все данные, что не проходят фильтр, будут удалены и из изначального списка
     * @param items список элементов
     * @param name строка, что должна быть в названии
     * @return Т с удаленными данными
     * @param <I> наследник Item
     * @param <T> любой список I
     */
    public static <I extends Item, T extends List<I>> T name_filter(T items, String name){
        items.removeIf(item -> !item.getName().toLowerCase().contains(name.toLowerCase()));
        return items;
    }
}
