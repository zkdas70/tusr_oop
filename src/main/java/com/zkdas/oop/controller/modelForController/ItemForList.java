package com.zkdas.oop.controller.modelForController;

import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;

public class ItemForList extends Item {
    /**
     * Класс Item для отображения в списке
     */
    public ItemForList(String name, String info, float cost, Category category) throws Exception {
        super(name, info, cost, category);
    }

    public <T extends Item> ItemForList(T item, boolean copyId) throws Exception {
        super(item, copyId);
    }

    public <T extends Item> ItemForList(T item) throws Exception {
        super(item);
    }

    @Override
    public String toString() {
        return _name.toString(); // Будет отображаться в ListView
    }
}
