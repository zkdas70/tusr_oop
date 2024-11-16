package com.zkdas.oop.controller;

import com.zkdas.oop.service.DataTools.Filter;
import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Item;
import com.zkdas.oop.model.Store;
import com.zkdas.oop.service.DataTools.SafeDataTools;
import com.zkdas.oop.service.Validators.DataRequiredValidator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

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
    @FXML
    private TextField FindTF;
    @FXML
    private CheckBox FindCB;
    @FXML
    private TextField CostTF;
    @FXML
    private CheckBox CostCB;
    @FXML
    private ChoiceBox<Category> CategoryChB;
    @FXML
    private CheckBox CategoryCB;

    // ListView
    @FXML
    private ListView<ItemForList> items_listView;

    private ItemForList selected_item = null; // -1 техническое значение (выбрано нечего)

    private ObservableList<ItemForList> items; // список элементов в ListView

    private HashMap<String, Filter<ItemForList>> filters = new HashMap<>(); // словарь активных фильтров

    private final Store store = new Store();

    /**
     * Очистит поля в правой части
     */
    private void clearFields() {
        id_field.setText("");
        cost_field.setText("");
        name_field.setText("");
        description_field.setText("");
    }

    /**
     * Метод для фильтрации элементов в listView
     */
    private void filter() {
        ObservableList<ItemForList> items = FXCollections.observableArrayList(this.items);

        // применение фильтров
        for (Filter<ItemForList> f : filters.values()) {
            items = (ObservableList<ItemForList>) f.get_list(items);
        }

        // сброс выбранного элемента
        selected_item = null;
        // отчистка полей
        clearFields();
        // установка отфильтрованных элементов в listView
        items_listView.setItems(items);
    }

    private void initializeFilters() {

        FindTF.addEventHandler(ActionEvent.ACTION, event -> {
            if (FindCB.isSelected()) {
                Filter<ItemForList> filter = (in_list) -> SafeDataTools.name_filter(in_list, FXCollections.observableArrayList(), FindTF.getText());
                filters.put("Find", filter);
            }
            filter();
        });
        CostTF.addEventHandler(ActionEvent.ACTION, event -> {
            if (CostCB.isSelected()) {
                Filter<ItemForList> filter = (in_list) -> SafeDataTools.price_filter(in_list, FXCollections.observableArrayList(),
                        Double.parseDouble(CostTF.getText()));
                filters.put("Cost", filter);
            }

            filter();
        });

        // устанавливаю выбор значений
        CategoryChB.getItems().addAll(Category.values());
        // устанавливает выбор по умолчанию
        CategoryChB.setValue(Category.NONE);
        // обработчик событий
        CategoryChB.addEventHandler(ActionEvent.ACTION, event -> {
            if (CategoryCB.isSelected()) {
                Filter<ItemForList> filter = (in_list) -> SafeDataTools.category_filter(in_list, FXCollections.observableArrayList(), CategoryChB.getValue());
                filters.put("Category", filter);
            }
            filter();
        });

        FindCB.addEventHandler(ActionEvent.ACTION, event -> {
            if (FindCB.isSelected()) {
                Filter<ItemForList> filter = (in_list) -> SafeDataTools.name_filter(in_list, FXCollections.observableArrayList(), FindTF.getText());
                filters.put("Find", filter);
                return;
            }
            filters.remove("Find");
            filter();
        });
        CostCB.addEventHandler(ActionEvent.ACTION, event -> {
            if (CostCB.isSelected()) {
                Filter<ItemForList> filter = (in_list) -> SafeDataTools.price_filter(in_list, FXCollections.observableArrayList(),
                        Double.parseDouble(CostTF.getText()));
                filters.put("Cost", filter);
                return;
            }
            filters.remove("Cost");
            filter();
        });
        CategoryCB.addEventHandler(ActionEvent.ACTION, event -> {
            if (CategoryCB.isSelected()) {
                Filter<ItemForList> filter = (in_list) -> SafeDataTools.category_filter(in_list, FXCollections.observableArrayList(), CategoryChB.getValue());
                filters.put("Category", filter);
                return;
            }
            filters.remove("Category");
            filter();
        });
    }

    public void initialize() {
        initializeFilters();
        // устанавливаю выбор значений
        Category_field.getItems().addAll(Category.values());

        // устанавливает выбор по умолчанию
        Category_field.setValue(Category.NONE);

        // запрет на изменение id_field
        id_field.setEditable(false);

        // Инициализация списка ListView
        items = store.getItems();
        items_listView.setItems(items);

        // Обработчик клика
        items_listView.setOnMouseClicked(event -> {
            MultipleSelectionModel<ItemForList> SelectionModel = items_listView.getSelectionModel();

            // получение выделенного элемента (как объекта)
            ItemForList selectedItem = SelectionModel.getSelectedItem();
            if (selectedItem != null) {
                // получение id выделенного элемента
                selected_item = SelectionModel.getSelectedItem();

                // задаем значения полям
                id_field.setText(String.valueOf(selectedItem.getId()));
                cost_field.setText(String.valueOf(selectedItem.getCost()));
                name_field.setText(selectedItem.getName());
                description_field.setText(selectedItem.getInfo());

                Category_field.setValue(selectedItem.getCategory());
            }
        });
    }

    public ArrayList<ItemForList> getItems() {
        return new ArrayList<>(items);
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
                    Float.parseFloat(cost_field.getText()), Category_field.getValue()));
            clearFields();
        }
    }

    @FXML
    private void remove_btn_click(ActionEvent ignoredE) {
        // обработчик нажатия на кнопку remove
        // если выбрано поле
        if (selected_item != null) {
            items.remove(selected_item);

            clearFields();
            selected_item = null;
        }
    }
}