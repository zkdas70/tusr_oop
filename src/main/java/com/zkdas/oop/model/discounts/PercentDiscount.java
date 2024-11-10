package com.zkdas.oop.model.discounts;

import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PercentDiscount implements IDiscount, Comparable<PercentDiscount>{
    @Override
    public int compareTo(@NotNull PercentDiscount obj) {
        return Integer.compare(this.percent, obj.percent);
    }

    public static double PERCENT_DISCOUNT = 0;

    private final Category category;
    private int percent;

    public PercentDiscount(Category category) {
        this.category = category;
        this.percent = 1;
    }

    /**
     * Базовый метод: возвращает описательную строку скидки
     *
     * @return описательная строка скидки (пример «процентная «{Категория}» - {Процент}%»)
     */
    @Override
    public String getInfo() {
        return "процентная «%s» - %s%%".formatted(category.toString(), percent);
    }

    /**
     * Базовый метод: подсчитывает размер скидки
     *
     * @param items список продуктов
     * @return возвращает размер скидки
     */
    @Override
    public <I extends Item, T extends List<I>> double Calculate(T items) {
        double price = 0;
        for (I item : items) {
            if (item.getCategory() == category) {
                price += item.getCost();
            }
        }

        return price * ((double) percent / 100);
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
        double discount = Calculate(items);
        percent = 1;
        Update(items);
        return discount;
    }

    /**
     * Базовый метод: добавляет баллы на основе полученного списка товаров
     *
     * @param items список продуктов
     */
    @Override
    public <I extends Item, T extends List<I>> void Update(T items) {
        double prise = 0;
        for (I item: items){
            if (item.getCategory() == category) {
                prise += item.getCost();
            }
        }
        percent += (int) Math.floor(prise / 1_000);
        if (percent > 10){
            percent = 10;
        }
    }
}
