package com.zkdas.oop.controller.modelForController;

import com.zkdas.oop.model.Category;
import com.zkdas.oop.model.Item;

public class ItemForList extends Item {
    /**
     * Класс Item для отображения в списке
     */
    public ItemForList(String name, String info, float cost, Category category) throws Exception {
        super(name, info, cost, category);
    }

    public <T extends Item> ItemForList(T item) throws Exception {
        this(item.getName(), item.getInfo(), item.getCost(), item.getCategory());
    }

    @Override
    public String toString() {
        return _name.toString(); // Будет отображаться в ListView
    }
}
