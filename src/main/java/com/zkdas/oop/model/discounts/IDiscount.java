package com.zkdas.oop.model.discounts;

import com.zkdas.oop.model.Item.Item;

import java.util.List;

/**
 * Базовый интерфейс скидки
 */
public interface IDiscount {
    /**
     * Базовый метод: возвращает описательную строку скидки
     * @return описательная строка скидки (пример «Накопительная – {Баллы} баллов»)
     */
    public String getInfo();

    /**
     * Базовый метод: подсчитывает размер скидки
     * @param items список продуктов
     * @return возвращает размер скидки
     * @param <I> наследник Item
     * @param <T> любой список
     */
    public <I extends Item, T extends List<I>> double Calculate(T items);

    /**
     * Базовый метод: применяет скидку к товарам
     * (Метод возвращает размер скидки, и при этом списывает накопленные баллы)
     * @param items список продуктов
     * @return возвращает размер скидки
     * @param <I> наследник Item
     * @param <T> любой список
     */
    public <I extends Item, T extends List<I>> double Apply(T items);

    /**
     * Базовый метод: добавляет баллы на основе полученного списка товаров
     * @param items список продуктов
     * @param <I> наследник Item
     * @param <T> любой список
     */
    public <I extends Item, T extends List<I>> void Update(T items);
}
