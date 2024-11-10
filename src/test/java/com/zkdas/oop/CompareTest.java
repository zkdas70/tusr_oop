package com.zkdas.oop;

import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;
import com.zkdas.oop.model.discounts.PercentDiscount;
import com.zkdas.oop.model.discounts.PointsDiscount;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CompareTest {
    @Test
    public void CompareItemTest() throws Exception {
        Item i1 = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);
        Item i2 = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);

        assertEquals(0, i1.compareTo(i2));

        i1 = new Item("лимон", "Тот самый взрывной", 50, Category.GROCERIES);
        i2 = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);

        assertEquals(1, i1.compareTo(i2));

        i1 = new Item("лимон", "Тот самый взрывной", 5, Category.GROCERIES);
        i2 = new Item("лимон", "Тот самый взрывной", 50, Category.GROCERIES);

        assertEquals(-1, i1.compareTo(i2));
    }

    @Test
    public void ComparePointsDiscountTest() throws Exception {
        PointsDiscount d1 = new PointsDiscount();
        PointsDiscount d2 = new PointsDiscount();

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("тестовый товар", "ntcn", 10_000, Category.NONE));

        assertEquals(0, d1.compareTo(d2));

        d1 = new PointsDiscount();
        d1.Update(items);
        d2 = new PointsDiscount();

        assertEquals(1, d1.compareTo(d2));

        d1 = new PointsDiscount();
        d2 = new PointsDiscount();
        d2.Update(items);

        assertEquals(-1, d1.compareTo(d2));
    }

    @Test
    public void ComparePercentDiscountTest() throws Exception {
        PercentDiscount d1 = new PercentDiscount(Category.NONE);
        PercentDiscount d2 = new PercentDiscount(Category.NONE);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("тестовый товар", "ntcn", 10_000, Category.NONE));

        assertEquals(0, d1.compareTo(d2));

        d1 = new PercentDiscount(Category.NONE);
        d1.Update(items);
        d2 = new PercentDiscount(Category.NONE);

        assertEquals(1, d1.compareTo(d2));

        d1 = new PercentDiscount(Category.NONE);
        d2 = new PercentDiscount(Category.NONE);
        d2.Update(items);

        assertEquals(-1, d1.compareTo(d2));
    }
}
