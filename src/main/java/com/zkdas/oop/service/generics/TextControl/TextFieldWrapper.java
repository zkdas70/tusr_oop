package com.zkdas.oop.service.generics.TextControl;

import javafx.scene.control.TextField;

public class TextFieldWrapper implements TextControl {
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
