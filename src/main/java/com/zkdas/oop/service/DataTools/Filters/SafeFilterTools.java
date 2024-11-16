package com.zkdas.oop.service.DataTools.Filters;

import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;

import java.util.List;

public class SafeFilterTools {
    /**
     * Добавит ссылки на элементы, что прошли фильтр в список items_out
     * @param items_in список элементов для фильтрации
     * @param items_out список отфильтрованных элементов
     * @param lowPrice нижний порог
     * @return Т с удаленными данными
     * @param <I> наследник Item
     * @param <T> любой список I
     */
    public static <I extends Item, T extends List<I>> T price_filter(T items_in, T items_out, double lowPrice){
        for (I item : items_in) {
            if (item.getCost() > lowPrice) {
                items_out.add(item);
            }
        }
        return items_out;
    }
    /**
     * Добавит ссылки на элементы, что прошли фильтр в список items_out
     * @param items_in список элементов для фильтрации
     * @param items_out список отфильтрованных элементов
     * @return Т с удаленными данными
     * @param <I> наследник Item
     * @param <T> любой список I
     */
    public static <I extends Item, T extends List<I>> T price_filter(T items_in, T items_out){
        return price_filter(items_in, items_out, 5_000);
    }

    /**
     * Добавит ссылки на элементы, что прошли фильтр в список items_out
     * @param items_in список элементов для фильтрации
     * @param items_out список отфильтрованных элементов
     * @param filter категория товара, что надо отфильтровать
     * @return Т с удаленными данными
     * @param <I> наследник Item
     * @param <T> любой список I
     */
    public static <I extends Item, T extends List<I>> T category_filter(T items_in, T items_out, Category filter){
        for (I item : items_in) {
            if (item.getCategory() == filter) {
                items_out.add(item);
            }
        }
        return items_out;
    }

    /**
     * Добавит ссылки на элементы, что прошли фильтр в список items_out
     * @param items_in список элементов для фильтрации
     * @param items_out список отфильтрованных элементов
     * @param name строка, что должна быть в названии
     * @return Т с удаленными данными
     * @param <I> наследник Item
     * @param <T> любой список I
     */
    public static <I extends Item, T extends List<I>> T name_filter(T items_in, T items_out, String name){
        for (I item : items_in) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                items_out.add(item);
            }
        }
        return items_out;
    }
}
