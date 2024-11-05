package com.zkdas.oop.model.discounts;

import com.zkdas.oop.model.Item.Item;

import java.util.List;

public class PointsDiscount implements IDiscount{
    public static final double MAX_DISCOUNT = 0.30;
    public static final double  POINTS_CONVERSION = 0.10;

    private int points = 0;

    /**
     * Базовый метод: возвращает описательную строку скидки
     *
     * @return описательная строка скидки («Накопительная – {Баллы} баллов»)
     */
    @Override
    public String getInfo() {
        return "Накопительная - %s баллов".formatted(points);
    }

    /**
     * Базовый метод: подсчитывает размер скидки
     *
     * @param items список продуктов
     * @return возвращает размер скидки
     */
    @Override
    public <I extends Item, T extends List<I>> double calculate(T items) {
        double prise = 0;
        for (I item : items) {
            prise += item.getCost();
        }
        return Math.min(points, Math.floor(MAX_DISCOUNT * prise));
    }

    /**
     * Базовый метод: применяет скидку к товарам
     * (Метод возвращает размер скидки, и при этом списывает накопленные баллы)
     *
     * @param items список продуктов
     * @return возвращает размер скидки
     */
    @Override
    public <I extends Item, T extends List<I>> double Apply(T items) {
        Update(items);
        double points = calculate(items);
        this.points -= (int) points;
        return points;
    }

    /**
     * Базовый метод: добавляет баллы на основе полученного списка товаров
     *
     * @param items список продуктов
     */
    @Override
    public <I extends Item, T extends List<I>> void Update(T items) {
        for (I item : items) {
            points += (int) Math.ceil(item.getCost() * POINTS_CONVERSION);
        }
    }
}
