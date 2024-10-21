package com.zkdas.oop.model;

import com.zkdas.oop.controller.modelForController.ItemForList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Cart {
    private ObservableList<ItemForList> items = FXCollections.observableArrayList();

    public Cart() {
    }

    public double getAmount(){
        double total = 0;

        for (ItemForList item : items){
            total += item.getCost();
        }

        return total;
    }

    public void addItem(ItemForList item){
        items.add(item);
    }

    /**
     * Удалит Item
     * @param item элемент, что надо удалить
     */
    public void removeItem(ItemForList item){
        items.remove(item);
    }

    /**
     * Удалит Item по id
     * @param itemId id элемента
     */
    public void removeItemById(int itemId){
        items.removeIf(item -> item.getId() == itemId);
    }

    public ObservableList<ItemForList> getItems(){
        return items;
    }
    public void setItems(ObservableList<ItemForList> items){
        this.items = items;
    }
}
