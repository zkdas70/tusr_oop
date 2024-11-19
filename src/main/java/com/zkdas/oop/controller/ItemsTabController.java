package com.zkdas.oop.controller;

import com.zkdas.oop.controller.modelForController.ItemForList;
import com.zkdas.oop.model.Item.Category;
import com.zkdas.oop.model.Item.Events.IItemEventListener;
import com.zkdas.oop.model.Item.Events.ItemEvent;
import com.zkdas.oop.model.Item.Item;
import com.zkdas.oop.model.Store;
import com.zkdas.oop.service.DataTools.DataTools;
import com.zkdas.oop.service.DataTools.Filters.SafeFilterTools;
import com.zkdas.oop.service.DataTools.Filters.Filter;
import com.zkdas.oop.service.Validators.DataRequiredValidator;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

enum OrderCBOptions{
    ID("id", (item1, item2) -> Integer.compare(item1.getId(), item2.getId())),
    NAME("названию", (item1, item2) -> item1.getName().compareTo(item2.getName())),
    COST("цене", (item1, item2) -> Double.compare(item1.getCost(), item2.getCost())),
    CATEGORY("категории", (item1, item2) -> Integer.compare(item1.getCategory().ordinal(), item2.getCategory().ordinal()));
    private String value;
    private Comparator<Item> comparator;

    /**
     * Создает перечисление
     * @param value
     * @param comparator
     */
    OrderCBOptions(String value, Comparator<Item> comparator){
        this.value = "Сортировка по " + value;
        this.comparator = comparator;
    }

    public Comparator<Item> getComparator() {
        return comparator;
    }

    @Override
    public String toString() {
        return value;
    }
}

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
    @FXML
    private ChoiceBox<OrderCBOptions> OrderChB;

    // ListView
    @FXML
    private ListView<ItemForList> items_listView;

    private ItemForList selected_item = null; // null техническое значение (выбрано нечего)

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
        ObservableList<ItemForList> items = this.store.getItems();

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
        Filter<ItemForList> FindFilter = (in_list) -> SafeFilterTools.name_filter(in_list,
                FXCollections.observableArrayList(), FindTF.getText());
        Filter<ItemForList> CostFilter = (in_list) -> SafeFilterTools.price_filter(in_list,
                FXCollections.observableArrayList(), Double.parseDouble(CostTF.getText()));
        Filter<ItemForList> CategoryFilter = (in_list) -> SafeFilterTools.category_filter(in_list,
                FXCollections.observableArrayList(), CategoryChB.getValue());

        FindTF.addEventHandler(ActionEvent.ACTION, event -> {
            if (FindCB.isSelected()) {
                filters.put("Find", FindFilter);
            }
            filter();
        });
        CostTF.addEventHandler(ActionEvent.ACTION, event -> {
            if (CostCB.isSelected()) {
                DataRequiredValidator validator = new DataRequiredValidator();
                validator.validateDoubleField(CostTF);
                if (!validator.IsNotErrors()) {
                    return;
                }
                filters.put("Cost", CostFilter);
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
                filters.put("Category", CategoryFilter);
            }
            filter();
        });

        // обработчики чекбокс
        FindCB.addEventHandler(ActionEvent.ACTION, event -> {
            if (FindCB.isSelected()) {
                filters.put("Find", FindFilter);
            } else {
                filters.remove("Find");
            }
            filter();
        });
        CostCB.addEventHandler(ActionEvent.ACTION, event -> {
            if (CostCB.isSelected()) {
                DataRequiredValidator validator = new DataRequiredValidator();
                validator.validateDoubleField(CostTF);
                if (!validator.IsNotErrors()) {
                    return;
                }
                filters.put("Cost", CostFilter);
            } else {
                filters.remove("Cost");
            }
            filter();
        });
        CategoryCB.addEventHandler(ActionEvent.ACTION, event -> {
            if (CategoryCB.isSelected()) {
                filters.put("Category", CategoryFilter);
            } else {
                filters.remove("Category");
            }
            filter();
        });
    }

    private void initializeSort(){
        // список с выбором сортировки
        OrderChB.getItems().addAll(OrderCBOptions.values());
        OrderChB.setValue(OrderCBOptions.NAME);
        OrderChB.setOnAction(event ->{
            DataTools.Sort(items_listView.getItems(), OrderChB.getSelectionModel().getSelectedItem().getComparator());
        });

        store.getItems().addListener(new ListChangeListener<ItemForList>() {
            @Override
            public void onChanged(Change<? extends ItemForList> c) {
                c.next();
                if (c.wasAdded() || c.wasRemoved()) {
                    filter();
                    DataTools.Sort(items_listView.getItems(), OrderChB.getSelectionModel().getSelectedItem().getComparator());
                }
            }

        });
    }

    private void initializeItemsList(){
        // Инициализация списка ListView
        items_listView.setItems(store.getItems());

        // Обработчик клика
        items_listView.setOnMouseClicked(event -> {
            MultipleSelectionModel<ItemForList> SelectionModel = items_listView.getSelectionModel();

            // получение выделенного элемента (как объекта)
            ItemForList selectedItem = SelectionModel.getSelectedItem();
            if (selectedItem != null) {
                // получение выделенного элемента
                selected_item = SelectionModel.getSelectedItem();

                this.refreshData();
            }
        });
    }

    public void initialize() {
        // устанавливаю выбор значений
        Category_field.getItems().addAll(Category.values());

        // устанавливает выбор по умолчанию
        Category_field.setValue(Category.NONE);

        // запрет на изменение id_field
        id_field.setEditable(false);

        initializeItemsList();
        initializeFilters();
        initializeSort();

        store.getItems().addListener(new ListChangeListener<ItemForList>() {
            @Override
            public void onChanged(Change<? extends ItemForList> c) {
                c.next();
                if (c.wasAdded()) {
                    for (Item item : c.getAddedSubList()){
                        item.addEventListener(Item.EventType.All, new IItemEventListener() {
                            @Override
                            public <T extends ItemEvent> void processEvent(T event) {
                                if (event.getSource() == selected_item){
                                    refreshData();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void refreshData() {
        // задаем значения полям
        id_field.setText(String.valueOf(this.selected_item.getId()));
        cost_field.setText(String.valueOf(this.selected_item.getCost()));
        name_field.setText(this.selected_item.getName());
        description_field.setText(this.selected_item.getInfo());

        Category_field.setValue(selected_item.getCategory());
    }

    public ArrayList<ItemForList> getItems() {
        return new ArrayList<>(store.getItems());
    }

    public <T extends Item> void addItem(T item) throws Exception {
        store.getItems().add(new ItemForList(item));
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
            store.getItems().add(new ItemForList(name_field.getText(), description_field.getText(),
                    Float.parseFloat(cost_field.getText()), Category_field.getValue()));
            clearFields();
        }
    }

    @FXML
    private void remove_btn_click(ActionEvent ignoredE) {
        // обработчик нажатия на кнопку remove
        // если выбрано поле
        if (selected_item != null) {
            store.getItems().remove(selected_item);

            clearFields();
            selected_item = null;
        }
    }
}