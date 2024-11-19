package com.zkdas.oop.model.discounts;

import com.zkdas.oop.model.Item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Класс бальной скидки
 */
public class PointsDiscount implements IDiscount, Comparable<PointsDiscount>{
    // константа максимальной скидки от стоимости
    public static final double MAX_DISCOUNT = 0.30;
    // константа конверсии стоимости покупки в балы
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
    public <I extends Item, T extends List<I>> double Calculate(T items) {
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
        double points = Calculate(items);
        this.points -= (int) points;
        Update(items);
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

    @Override
    public int compareTo(@NotNull PointsDiscount obj) {
        return Integer.compare(this.points, obj.points);
    }
}
