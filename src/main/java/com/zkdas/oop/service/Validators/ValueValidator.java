package com.zkdas.oop.service.Validators;

/**
* Валидатор проверки длины поля
*/
public class ValueValidator {
    public static void AssertStringOnLength(String value, int maxLength, String propertyName) throws Exception {
        if (value.length() > maxLength) {
            throw new Exception(propertyName + " должна быть короче " + maxLength + " символов.");
        }
    }

    public static void AssertStringOnLength(String value, int maxLength) throws Exception {
        AssertStringOnLength(value, maxLength, null);
    }
}
