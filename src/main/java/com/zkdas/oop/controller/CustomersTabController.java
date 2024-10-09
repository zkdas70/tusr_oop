package com.zkdas.oop.controller;

import com.zkdas.oop.controller.modelForController.CustomerForList;
import com.zkdas.oop.model.Address;
import com.zkdas.oop.model.Customer;
import com.zkdas.oop.model.Store;
import com.zkdas.oop.service.Validators.DataRequiredValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class CustomersTabController {
    /**
     * Контролер виджета CustomersTab
     */
    @FXML
    private VBox interfaceContainer;

    private AddressController addressController;
    // текстовые поля
    @FXML
    private TextField id_field;
    @FXML
    private TextField ful_name_field;
    // ListView
    @FXML
    private ListView<CustomerForList> customers_listView;

    private int selected_index = -1;// -1 техническое значение (выбрано нечего)

    private ObservableList<CustomerForList> items; // список элементов в ListView
    private Address address;

    private void clearField() {
        id_field.setText("");
        ful_name_field.setText("");
    }

    public void initialize() throws IOException {
        // запрет на изменение id_field
        id_field.setEditable(false);

        // указания пути к fxml файлу
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zkdas/oop/AddressControl.fxml"));
        Parent subview = loader.load();// загрузка данных из файла
        addressController = loader.getController(); // присвоение контролера
        // вставка subview
        interfaceContainer.getChildren().add(subview);

        // Инициализация списка ListView
        Store store = new Store();
        items = store.getCustomers();
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
                addressController.SetAddress(selectedItem.getAddress());
            }
        });
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
        // валидация полей Адреса
        Address address = addressController.getAddressFromFields(validator);

        // если все поля прошли валидацию
        if (validator.IsNotErrors()) {
            items.add(new CustomerForList(ful_name_field.getText(), address));

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
