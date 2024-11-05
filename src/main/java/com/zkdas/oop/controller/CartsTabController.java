package com.zkdas.oop.controller;

import com.zkdas.oop.controller.modelForController.CustomerForList;
import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Store;
import com.zkdas.oop.model.discounts.IDiscount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

class CheckBoxStruct {
    /**
     * Аля структура
     */
    public CheckBox checkBox;
    public IDiscount discount;
}

public class CartsTabController {
    @FXML
    private ListView<ItemForList> items_listView;
    @FXML
    private ListView<ItemForList> cart_listView;
    @FXML
    private ChoiceBox<CustomerForList> customer_ChoseBox;
    @FXML
    private Label AmountLabel;
    @FXML
    private Label DiscoutAmountLabel;
    @FXML
    private Label TotalLabel;
    @FXML
    private VBox DiscountsPlane;
    private ArrayList<CheckBoxStruct> discounts = new ArrayList<>();
    private double total_discount = 0.0; // итоговая скидка по всем товарам

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

    private void updateDiscounts() {
        CustomerForList customer = getSelectedCustomer();
        if (customer == null) {
            return;
        }
        total_discount = 0;
        for (CheckBoxStruct discount : discounts) {
            if (discount.checkBox.isSelected()) {
                total_discount += discount.discount.Calculate(customer.get_cart().getItems());
            }
        }

        DiscoutAmountLabel.setText(String.valueOf(total_discount));
        TotalLabel.setText(String.valueOf(customer.get_cart().getAmount() - total_discount));
    }

    private void updateCost() {
        CustomerForList customer = getSelectedCustomer();
        if (customer == null) {
            return;
        }
        discounts.clear();
        DiscountsPlane.getChildren().clear();
        for (IDiscount i : customer.get_discounts()) {
            CheckBoxStruct checkBoxStruct = new CheckBoxStruct();
            // создаю чекбокс
            checkBoxStruct.checkBox = new CheckBox();
            checkBoxStruct.checkBox.setText(i.getInfo());
            checkBoxStruct.checkBox.setOnAction(event -> updateDiscounts());
            // сохраняю ссылку на скидку
            checkBoxStruct.discount = i;

            DiscountsPlane.getChildren().add(checkBoxStruct.checkBox);

            discounts.add(checkBoxStruct);
        }

        AmountLabel.setText(String.valueOf(customer.get_cart().getAmount()));
        updateDiscounts();
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

            // обработка ссылок
            total_discount = 0.0;
            for (CheckBoxStruct discount : discounts) {
                if (discount.checkBox.isSelected()) { // списание балов
                    total_discount += discount.discount.Apply(selectedCustomer.get_cart().getItems());
                    continue;
                }
                // накапливание балов
                discount.discount.Update(selectedCustomer.get_cart().getItems());

            }
            store.getOrders().add(selectedCustomer.createOrder(total_discount));

            updateCost();
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
