package com.zkdas.oop.service.generics.TextControl;
import javafx.scene.control.TextArea;

public class TextAreaWrapper implements TextControl {
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