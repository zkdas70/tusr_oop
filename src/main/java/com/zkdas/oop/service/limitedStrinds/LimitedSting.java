package com.zkdas.oop.service.limitedStrinds;

/// <summary>
///  класс лимитированой по длине строки
/// </summary>
public class LimitedSting {
    private int _length;
    private String _data;

    public LimitedSting(int length, String data) throws Exception {
        _length = length;
        _check_length(data);
    }

    private void _check_length(String data) throws Exception {
        if (_length < data.length()) {
            throw new Exception("привышена максимальная длина поля в " + _length + " символов");
        }

        _data = data;
    }

    public void setData(String data) throws Exception {
        _check_length(data);
    }

    @Override
    public String toString() {
        return _data;
    }
}

