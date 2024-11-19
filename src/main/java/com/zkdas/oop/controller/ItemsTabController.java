package com.zkdas.oop.controller;

import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;
import com.zkdas.oop.model.Store;
import com.zkdas.oop.service.Validators.DataRequiredValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Контролер виджета ItemsTab
 */
public class ItemsTabController {
    @FXML
    private ChoiceBox<Category> Category_field;
    // текстовые поля
    @FXML
    private TextField id_field;
    @FXML
    private TextField cost_field;
    @FXML
    private TextArea name_field;
    @FXML
    private TextArea description_field;
    // ListView
    @FXML
    private ListView<ItemForList> items_listView;

    private int selected_index = -1; // -1 техническое значение (выбрано нечего)

    private ObservableList<ItemForList> items; // список элементов в ListView

    /**
     * Очистит поля во вкладке
     */
    private void clearFields() {
        id_field.setText("");
        cost_field.setText("");
        name_field.setText("");
        description_field.setText("");
    }

    /**
     * Инициализация контролера
     */
    public void initialize() {
        // устанавливаю выбор значений
        Category_field.getItems().addAll(Category.values());

        // устанавливает выбор по умолчанию
        Category_field.setValue(Category.NONE);

        // запрет на изменение id_field
        id_field.setEditable(false);

        // Инициализация списка ListView
        Store store = new Store();
        items = store.getItems();
        items_listView.setItems(items);

        // Обработчик клика
        items_listView.setOnMouseClicked(event -> {
            MultipleSelectionModel<ItemForList> SelectionModel = items_listView.getSelectionModel();

            // получение выделенного элемента (как объекта)
            ItemForList selectedItem = SelectionModel.getSelectedItem();
            if (selectedItem != null) {
                // получение id выделенного элемента
                selected_index = SelectionModel.getSelectedIndex();

                // задаем значения полям
                id_field.setText(String.valueOf(selectedItem.getId()));
                cost_field.setText(String.valueOf(selectedItem.getCost()));
                name_field.setText(selectedItem.getName());
                description_field.setText(selectedItem.getInfo());

                Category_field.setValue(selectedItem.getCategory());
            }
        });
    }

    /**
     * Добавит элемент в глобальный список элементов
     * УСТАРЕЛО
     */
    public <T extends Item> void addItem(T item) throws Exception {
        items.add(new ItemForList(item));
    }
    /**
     * Обработчик нажатия на кнопку add
     */
    @FXML
    private void add_btn_click(ActionEvent ignoredE) throws Exception {
        // обработчик нажатия на кнопку add
        DataRequiredValidator validator = new DataRequiredValidator();

        // валидация полей
        //validator.validateField(id_field);
        validator.validateNumberField(cost_field, 0, 100_000);
        validator.validateField(name_field, 200);
        validator.validateField(description_field, 1000);

        // если все поля прошли валидацию
        if (validator.IsNotErrors()) {
            items.add(new ItemForList(name_field.getText(), description_field.getText(),
                    Float.parseFloat(cost_field.getText()), Category_field.getValue()));
            clearFields();
        }
    }

    /**
     * Обработчик нажатия на кнопку remove
     */
    @FXML
    private void remove_btn_click(ActionEvent ignoredE) {
        // если выбрано поле
        if (selected_index >= 0) {
            items.remove(selected_index);
            clearFields();
            selected_index = -1;
        }
//        Store store = new Store();
//        store.getCustomers().clear();
    }
}