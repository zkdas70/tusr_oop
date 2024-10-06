package com.zkdas.oop.controller;

import com.zkdas.oop.model.Customer;
import com.zkdas.oop.service.Validators.DataRequiredValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Collection;


class CustomerForList extends Customer {
    public CustomerForList(String fulname, String address) throws Exception {
        super(fulname, address);
    }

    public <T extends Customer> CustomerForList(T customer) throws Exception {
        this(customer.getFulname(), customer.getAddress());
    }

    @Override
    public String toString() {
        return _fulname.get_data(); // Будет отображаться в ListView
    }
}

public class CustomersTabController {
    // текстовые поля
    @FXML
    private TextField id_field;
    @FXML
    private TextField ful_name_field;
    @FXML
    private TextArea address_field;
    // ListView
    @FXML
    private ListView<CustomerForList> customers_listView;

    private int selected_index = -1;// -1 техническое значение (выбрано нечего)

    private ObservableList<CustomerForList> items; // список элементов в ListView

    private void clearField() {
        id_field.setText("");
        ful_name_field.setText("");
        address_field.setText("");
    }

    public void initialize() {
        // запрет на изменение id_field
        id_field.setEditable(false);

        // Инициализация списка ListView
        items = FXCollections.observableArrayList();
        customers_listView.setItems(items);

        // Обработчик клика
        customers_listView.setOnMouseClicked(event -> {
            MultipleSelectionModel<CustomerForList> SelectionModel = customers_listView.getSelectionModel();

            // получение выделенного элемента (как объекта)
            CustomerForList selectedItem = SelectionModel.getSelectedItem();
            if (selectedItem != null) {
                // получение id выделенного элемента
                selected_index = SelectionModel.getSelectedIndex();

                // задаем значения полям
                id_field.setText(String.valueOf(selectedItem.getId()));
                ful_name_field.setText(selectedItem.getFulname());
                address_field.setText(selectedItem.getAddress());
            }
        });
    }

    public ArrayList<CustomerForList> getCustomers(){
        return new ArrayList<>(this.items);
    }

    public <T extends Customer> void setCustomers(ArrayList<T> customers) throws Exception {
        items.clear();

        for (T customer : customers) {
            items.add(new CustomerForList(customer));
        }
    }

    public <T extends Customer> void addCustomer(T customer) throws Exception {
        items.add(new CustomerForList(customer));
    }

    @FXML
    private void add_btn_click(ActionEvent ignoredE) throws Exception {
        // обработчик нажатия на кнопку add
        DataRequiredValidator validator = new DataRequiredValidator();

        // валидация полей (не должны быть пустыми)
        //validator.validateField(id_field);
        validator.validateField(ful_name_field, 200);
        validator.validateField(address_field, 500);

        // если все поля прошли валидацию
        if (validator.IsNotErrors()) {
            items.add(new CustomerForList(ful_name_field.getText(), address_field.getText()));

            clearField();
        }
    }

    @FXML
    private void remove_btn_click(ActionEvent ignoredE) {
        // обработчик нажатия на кнопку remove
        if (selected_index >= 0) {
            items.remove(selected_index);
            clearField();
            selected_index = -1;
        }
    }
}
