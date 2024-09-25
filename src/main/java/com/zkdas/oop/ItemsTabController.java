package com.zkdas.oop;

import com.zkdas.oop.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

interface TextControl {
    String getText();

    String getId();

    void setStyle(String style);
}

class TextFieldWrapper implements TextControl {
    private final TextField field;

    public TextFieldWrapper(TextField textField) {
        this.field = textField;
    }

    @Override
    public String getText() {
        return field.getText();
    }

    @Override
    public String getId() {
        return field.getId();
    }

    @Override
    public void setStyle(String style) {
        field.setStyle(style);
    }
}

class TextAreaWrapper implements TextControl {
    private final TextArea field;

    public TextAreaWrapper(TextArea textField) {
        this.field = textField;
    }

    @Override
    public String getText() {
        return field.getText();
    }

    @Override
    public String getId() {
        return field.getId();
    }

    @Override
    public void setStyle(String style) {
        field.setStyle(style);
    }
}

class ItemValidator {
    private int _errors_counter = 0;

    public <T extends TextControl> void validateField(T field) {
        try {
            if (field.getText().isEmpty()) {
                throw new Exception("поле" + field.getId() + "пустое");
            }
            field.setStyle(""); // Сбросить стиль, чтобы вернуть стандартный;
        } catch (Exception ex) {
            _errors_counter++;
            field.setStyle("-fx-background-color: red;");
        }
    }

    public boolean IsNotErrors() {
        return _errors_counter == 0;
    }
}

class ItemForList extends Item {
    public ItemForList(String name, String info, float cost) throws Exception {
        super(name, info, cost);
    }

    @Override
    public String toString() {
        return _name.get_data(); // Будет отображаться в ListView
    }
}

public class ItemsTabController {
    // кнопки
    @FXML
    private Button add_btn;
    @FXML
    private Button remove_btn;
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

    private  int selected_index = -1;

    private ObservableList<ItemForList> items; // список элементов в ListView

    private void clearField(){
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

            ItemForList selectedItem = SelectionModel.getSelectedItem();
            if (selectedItem != null) {
                System.out.println("ID выбранного элемента: " + selectedItem.getId());

                selected_index = SelectionModel.getSelectedIndex();

                id_field.setText(String.valueOf(selectedItem.getId()));
                cost_field.setText(String.valueOf(selectedItem.getCost()));
                name_field.setText(selectedItem.getName());
                description_field.setText(selectedItem.getInfo());
            }
        });
    }


    @FXML
    private void add_btn_click(ActionEvent e) throws Exception {
        // обработчик нажатия на кноку remove
        System.out.print("remove_btn_click\n");

        ItemValidator validator = new ItemValidator();

        //validator.validateField(id_field);
        validator.validateField(new TextFieldWrapper(cost_field));
        validator.validateField(new TextAreaWrapper(name_field));
        validator.validateField(new TextAreaWrapper(description_field));

        if (validator.IsNotErrors()) {
            items.add(new ItemForList(name_field.getText(), description_field.getText(),
                    Float.parseFloat(cost_field.getText())));
            clearField();
        }
    }

    @FXML
    private void remove_btn_click(ActionEvent e) throws Exception {
        // обработчик нажатия на кноку add
        if (selected_index >= 0) {
            items.remove(selected_index);
        }
        clearField();
    }
}