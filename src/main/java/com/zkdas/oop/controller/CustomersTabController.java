package com.zkdas.oop.controller;

import com.zkdas.oop.controller.modelForController.CustomerForList;
import com.zkdas.oop.model.Customer.Address;
import com.zkdas.oop.model.Customer.Customer;
import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Store;
import com.zkdas.oop.model.discounts.IDiscount;
import com.zkdas.oop.model.discounts.PercentDiscount;
import com.zkdas.oop.model.discounts.PointsDiscount;
import com.zkdas.oop.service.Validators.DataRequiredValidator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;

/**
 * Контролер виджета CustomersTab
 */
public class CustomersTabController {

    @FXML
    private VBox interfaceContainer;

    private AddressController addressController;

    @FXML
    private CheckBox isPriority;
    // текстовые поля
    @FXML
    private TextField id_field;
    @FXML
    private TextField ful_name_field;
    // ListView
    @FXML
    private ListView<CustomerForList> customers_listView;
    @FXML
    private ListView<IDiscount> discounts_listView;

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
        // вставка subview (его индекс в V боксе 2)
        interfaceContainer.getChildren().add(2, subview);

        // Инициализация списка ListView
        Store store = new Store();
        items = store.getCustomers();
        customers_listView.setItems(items);

        // Обработчик клика customers_listView
        customers_listView.setOnMouseClicked(event -> {
            MultipleSelectionModel<CustomerForList> SelectionModel = customers_listView.getSelectionModel();

            // получение выделенного элемента (как объекта)
            CustomerForList selectedCustomer = SelectionModel.getSelectedItem();
            if (selectedCustomer != null) {
                // получение id выделенного элемента
                selected_index = SelectionModel.getSelectedIndex();

                // задаем значения полям
                id_field.setText(String.valueOf(selectedCustomer.getId()));
                ful_name_field.setText(selectedCustomer.getFulname());
                addressController.SetAddress(selectedCustomer.getAddress());
                isPriority.setSelected(selectedCustomer.is_Priority());

                discounts_listView.setItems(selectedCustomer.get_discounts());
            }
        });

        isPriority.setOnAction(event -> {
            CustomerForList selectedItem = customers_listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                selectedItem.set_isPriority(isPriority.isSelected());
            }
        });

        // задаем
        discounts_listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(IDiscount item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    return;
                }
                setText(item.getInfo());
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
            items.add(new CustomerForList(ful_name_field.getText(), address, isPriority.isCache()));

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

    @FXML
    private void add_discount_btn_click(ActionEvent ignoredE) {
        if (selected_index < 0) {
            return;
        }

        // обработчик нажатия на кнопку add

        // диалоговое окно
        ChoiceDialog<Category> dialog = new ChoiceDialog<>(Category.NONE, Category.values());
        dialog.setTitle("Add Discount");
        dialog.setHeaderText("Choose discount");

        // Отображение диалогового окна и получение результата
        Optional<Category> result = dialog.showAndWait();

        result.ifPresent(r -> customers_listView.getSelectionModel().getSelectedItem().get_discounts().add(new PercentDiscount(result.get())));


    }

    @FXML
    private void remove_discount_btn_click(ActionEvent ignoredE) {
        // обработчик нажатия на кнопку remove
        IDiscount discount = discounts_listView.getSelectionModel().getSelectedItem();
        if (discount != null && selected_index > -1 && discount.getClass() != PointsDiscount.class) {
            customers_listView.getSelectionModel().getSelectedItem().get_discounts().remove(discount);
        }
    }
}
