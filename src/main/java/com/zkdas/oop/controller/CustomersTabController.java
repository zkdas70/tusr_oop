package com.zkdas.oop.controller;

import com.zkdas.oop.model.Customer;
import com.zkdas.oop.service.DataRequiredValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


class CustomerForList extends Customer{
    public CustomerForList(String fulname, String address) throws Exception {
        super(fulname, address);
    }

    @Override
    public String toString() {
        return _fulname.get_data(); // Будет отображаться в ListView
    }
}

public class CustomersTabController {
    // кнопки
    @FXML
    private Button add_btn;
    @FXML
    private Button remove_btn;
    // текстовые поля
    @FXML
    private TextField id_field;
    @FXML
    private TextField ful_name_field;
    @FXML
    private TextArea address_field;
    // ListView
    @FXML
    private ListView<CustomerForList> сustomers_listView;

    private  int selected_index = -1;// -1 техническое значени (выбрано нечего)

    private ObservableList<CustomerForList> items; // список элементов в ListView


    private void clearField(){
        id_field.setText("");
        ful_name_field.setText("");
        address_field.setText("");
    }

    public void initialize() {
        // запрет на изменение id_field
        id_field.setEditable(false);

        // Инициализация списка ListView
        items = FXCollections.observableArrayList();
        сustomers_listView.setItems(items);

        // Обработчик клика
        сustomers_listView.setOnMouseClicked(event -> {
            MultipleSelectionModel<CustomerForList> SelectionModel = сustomers_listView.getSelectionModel();

            // получение выделеного элемента (как обьекта)
            CustomerForList selectedItem = SelectionModel.getSelectedItem();
            if (selectedItem != null) {
                // получение id выделеного элемента
                selected_index = SelectionModel.getSelectedIndex();

                // задаем значения полям
                id_field.setText(String.valueOf(selectedItem.getId()));
                ful_name_field.setText(selectedItem.getFulname());
                address_field.setText(selectedItem.getAddress());
            }
        });
    }


    @FXML
    private void add_btn_click(ActionEvent e) throws Exception {
        // обработчик нажатия на кноку add
        DataRequiredValidator validator = new DataRequiredValidator();

        // валидация полей
        //validator.validateField(id_field);
        validator.validateField(ful_name_field);
        validator.validateField(address_field);

        // если все поля прошли валедацию
        if (validator.IsNotErrors()) {
            items.add(new CustomerForList(ful_name_field.getText(), address_field.getText()));

            clearField();
        }
    }

    @FXML
    private void remove_btn_click(ActionEvent e) throws Exception {
        // обработчик нажатия на кноку remove
        if (selected_index >= 0) {
            items.remove(selected_index);
            clearField();
            selected_index = -1;
        }
    }
}
