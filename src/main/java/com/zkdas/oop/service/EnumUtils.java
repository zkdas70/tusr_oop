package com.zkdas.oop.service;

import java.util.Random;

public class EnumUtils {
    // Метод для получения случайного значения enum
    public static <T extends Enum<?>> T getRandomEnum(Class<T> enumClass) {
        Random random = new Random();
        int index = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[index];
    }
}
