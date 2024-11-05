package com.zkdas.oop.model.Customer;

import com.zkdas.oop.controller.modelForController.ItemForList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Cart implements Cloneable {
    private ObservableList<ItemForList> items = FXCollections.observableArrayList();

    /**
     * Пустой конструктор Cart
     */
    public Cart() {
    }

    /**
     * Копирующий конструктор Cart
     * @param cart наследник Cart
     */
    public <T extends Cart> Cart(T cart){
        items.setAll(FXCollections.observableArrayList(cart.getItems()));
    }

    @Override
    public Cart clone() {
        Cart clone = new Cart();
        clone.setItems(FXCollections.observableArrayList(items));
        return clone;
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
