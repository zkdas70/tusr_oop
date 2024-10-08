package com.zkdas.oop.service.limitedStrinds;


public class LimitedSting {
    /**
     * Класс лимитированной по длине строки
     */
    private int _length;
    private String _data;

    /**
     * конструктор LimitedSting
     * @param length максимальная длина строки
     */
    public LimitedSting(int length) throws Exception {
        _length = length;
    }

    private void _check_length(String data) throws Exception {
        if (_length < data.length()) {
            throw new Exception("привышена максимальная длина поля в " + _length + " символов");
        }

        _data = data;
    }

    /**
     * Задаст новое значение строки
     * @param data строка, что должна быть задана
     */
    public void setData(String data) throws Exception {
        _check_length(data);
    }

    /**
     * Вернет максимально допустимую длину
     * @return int максимальная длина
     */
    public int getLength() {
        return _length;
    }

    public String toString() {
        return _data;
    }
}

