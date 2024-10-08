package com.zkdas.oop.controller;

import com.zkdas.oop.model.Item;
import com.zkdas.oop.service.Validators.DataRequiredValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.ArrayList;


class ItemForList extends Item {
    public ItemForList(String name, String info, float cost) throws Exception {
        super(name, info, cost);
    }

    public <T extends Item> ItemForList(T item) throws Exception {
        this(item.getName(), item.getInfo(), item.getCost());
    }

    @Override
    public String toString() {
        return _name.toString(); // Будет отображаться в ListView
    }
}

public class ItemsTabController {
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

    private void clearFields() {
        id_field.setText("");
        cost_field.setText("");
        name_field.setText("");
        description_field.setText("");
    }

    public void initialize() {
        // запрет на изменение id_field
        id_field.setEditable(false);

        // Инициализация списка ListView
        items = FXCollections.observableArrayList();
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
            }
        });
    }

    public ArrayList<ItemForList> getItems() {
        return new ArrayList<>(items);
    }

    public <T extends Item> void setItems(ArrayList<T> items) throws Exception {
        this.items.clear();

        for (T item : items) {
            this.items.add(new ItemForList(item));
        }
    }

    public <T extends Item> void addItem(T item) throws Exception {
        items.add(new ItemForList(item));
    }

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
                    Float.parseFloat(cost_field.getText())));
            clearFields();
        }
    }

    @FXML
    private void remove_btn_click(ActionEvent ignoredE) {
        // обработчик нажатия на кнопку remove
        // если выбрано поле
        if (selected_index >= 0) {
            items.remove(selected_index);
            clearFields();
            selected_index = -1;
        }
    }
}