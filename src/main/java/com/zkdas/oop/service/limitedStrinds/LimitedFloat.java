package com.zkdas.oop.service.limitedStrinds;

/// <summary>
///  класс лимитированой по длине Float числа
/// </summary>
public class LimitedFloat {
    private int _min;
    private int _max;
    private float _vale;

    public LimitedFloat(int min, int max, float vale) throws Exception {
        _min = min;
        _max = max;
        _check_vale(vale);
    }

    private void _check_vale(float vale) throws Exception {
        if (vale <= _min || vale >= _max) {
            throw new Exception("выход за огранмченый е приделы " + _min + " <=x<= " + _max);
        }

        _vale = vale;
    }

    public void set_value(float vale) throws Exception {
        _check_vale(vale);
    }

    public float get_value() {
        return _vale;
    }

    @Override
    public String toString() {
        return String.valueOf(_vale);
    }
}
