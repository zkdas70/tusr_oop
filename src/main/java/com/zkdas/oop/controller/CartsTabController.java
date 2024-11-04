package com.zkdas.oop.controller;

import com.zkdas.oop.controller.modelForController.CustomerForList;
import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Order;
import com.zkdas.oop.model.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CartsTabController {
    @FXML
    private ListView<ItemForList> items_listView;
    @FXML
    private ListView<ItemForList> cart_listView;
    @FXML
    private ChoiceBox<CustomerForList> customer_ChoseBox;
    @FXML
    private Label AmountLabel;

    public void initialize() {
        Store store = new Store();
        // Инициализация списка ListView
        items_listView.setItems(store.getItems());

        // инициализация выпадаюшего списка
        customer_ChoseBox.setItems(store.getCustomers());
    }

    private CustomerForList getSelectedCustomer() {
        return customer_ChoseBox.getValue();
    }

    private void updateCost() {
        CustomerForList customer = getSelectedCustomer();
        if (customer != null) {
            AmountLabel.setText(String.valueOf(customer.get_cart().getAmount()));
        }

    }

    @FXML
    private void add_btn_click(ActionEvent ignoredE) {
        // получаем выбраный элемент
        ItemForList selectedItem = items_listView.getSelectionModel().getSelectedItem();

        // не должен быть пустым
        if (selectedItem != null && getSelectedCustomer() != null) {
            getSelectedCustomer().get_cart().addItem(selectedItem);
            updateCost();
        }
    }

    @FXML
    private void clear_btn_click(ActionEvent ignoredE) {
        CustomerForList selectedCustomer = getSelectedCustomer();
        if (selectedCustomer != null) {
            selectedCustomer.get_cart().getItems().clear();
            updateCost();
        }
    }

    @FXML
    private void remove_btn_click(ActionEvent ignoredE) {
        CustomerForList selectedCustomer = getSelectedCustomer();
        ItemForList selectedItem = cart_listView.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null && selectedItem != null) {
            selectedCustomer.get_cart().removeItem(selectedItem);
            updateCost();
        }
    }

    @FXML
    private void create_btn_click(ActionEvent ignoredE) {
        CustomerForList selectedCustomer = getSelectedCustomer();
        if (selectedCustomer != null && !selectedCustomer.get_cart().getItems().isEmpty()) {
            Store store = new Store();
            store.getOrders().add(selectedCustomer.createOrder());
        }
    }

    @FXML
    private void CustomerListClick(ActionEvent ignoredE) {
        CustomerForList selectedCustomer = getSelectedCustomer();
        if (selectedCustomer != null) {
            cart_listView.setItems(selectedCustomer.get_cart().getItems());
            cart_listView.refresh(); // обновит список listView (автоматичесик не всегда вызывается)
            updateCost();
        }
    }
}
