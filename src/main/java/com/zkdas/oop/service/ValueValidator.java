package com.zkdas.oop.service;

/// <summary>
///  валидатор проверки длины поля
/// </summary>
public class ValueValidator {
    public static void AssertStringOnLength(String value, int maxLength, String propertyName) throws Exception {
        if (value.length() > maxLength) {
            throw new Exception(propertyName + " должна быть короче " + maxLength + "символов.");
        }
    }
}
