package com.zkdas.oop.service.Validators;

import javafx.scene.control.TextInputControl;

/// класс считающий сработавшие валидаторы
public class DataRequiredValidator {
    private int _errors_counter = 0;

    public <T extends TextInputControl> void validateField(T field) {
        /// проведет валидацию поля и окрасит его в красный если валидация провалена
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

    public <T extends TextInputControl> void validateNumberField(T field) {
        /// проведет валидацию поля и окрасит его в красный если валидация провалена
        try {
            if (field.getText().isEmpty()) {
                throw new Exception("поле" + field.getId() + "пустое");
            }
            Float.parseFloat(field.getText());
            field.setStyle(""); // Сбросить стиль, чтобы вернуть стандартный;
        } catch (Exception ex) {
            _errors_counter++;
            field.setStyle("-fx-background-color: red;");
        }
    }

    public boolean IsNotErrors() {
        /// вернет true если все валидаторы не сработали
        return _errors_counter == 0;
    }
}
