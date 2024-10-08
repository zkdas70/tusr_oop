package com.zkdas.oop.service.Validators;

import com.zkdas.oop.model.Address;
import javafx.scene.control.TextInputControl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

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

    public <T extends TextInputControl> void validateField(T field, int maxLen) {
        /// проведет валидацию поля и окрасит его в красный если валидация провалена
        try {
            if (field.getText().isEmpty()) {
                throw new Exception("поле" + field.getId() + "пустое");
            }
            ValueValidator.AssertStringOnLength(field.getText(), maxLen);

            field.setStyle(""); // Сбросить стиль, чтобы вернуть стандартный;
        } catch (Exception ex) {
            _errors_counter++;
            field.setStyle("-fx-background-color: red;");
        }
    }

    public <T extends TextInputControl> void validateNumberField(T field, float min, float max) {
        /// проведет валидацию поля и окрасит его в красный если валидация провалена
        try {
            if (field.getText().isEmpty()) {
                throw new Exception("поле" + field.getId() + "пустое");
            }
            float temp = Float.parseFloat(field.getText());
            if (min >= temp || temp >= max) {
                throw new Exception(field.getId());
            }
            field.setStyle(""); // Сбросить стиль, чтобы вернуть стандартный;
        } catch (Exception ex) {
            _errors_counter++;
            field.setStyle("-fx-background-color: red;");
        }
    }

    public <T extends TextInputControl> void validatePostField(T field) {
        /// проведет валидацию поля и окрасит его в красный если валидация провалена
        try {
            if (field.getText().isEmpty()) {
                throw new Exception("поле" + field.getId() + "пустое");
            }
            float temp = Integer.parseInt(field.getText());
            if (100_000 > temp || temp >= 1_000_000) {
                throw new Exception("Post index out of bounds");
            }
            field.setStyle(""); // Сбросить стиль, чтобы вернуть стандартный;
        } catch (Exception ex) {
            _errors_counter++;
            field.setStyle("-fx-background-color: red;");
        }
    }

    public void add_error() {
        _errors_counter++;
    }


    public boolean IsNotErrors() {
        /// вернет true если все валидаторы не сработали
        return _errors_counter == 0;
    }
}
