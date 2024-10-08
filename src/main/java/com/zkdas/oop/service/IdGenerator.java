package com.zkdas.oop.service;

public class IdGenerator {
    /**
     * Класс для генерации id
     */
    private int _lastId; // последний занятый id

    /**
     * Конструктор (инициализирует поле _lastId)
     */
    public IdGenerator() {
        _lastId = 0;
    }

    /**
     * Вернет следующий id
     * @return int число
     */
    public int get_next_id() {
        _lastId++;
        return _lastId;
    }
}
