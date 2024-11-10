package com.zkdas.oop.service.LimitedFields;


import java.util.Objects;

public class LimitedSting implements Cloneable {
    /**
     * Класс лимитированной по длине строки
     */
    private final int _length;
    private String _data;

    @Override
    public LimitedSting clone() {
        try {
            return  (LimitedSting) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LimitedSting) {
            return Objects.equals(obj.toString(), _data);
        }
        return false;
    }

    /**
     * конструктор LimitedSting
     * @param length максимальная длина строки
     */
    public LimitedSting(int length) {
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

    @Override
    public String toString() {
        return _data;
    }
}

