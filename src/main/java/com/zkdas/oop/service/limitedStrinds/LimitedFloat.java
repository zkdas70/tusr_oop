package com.zkdas.oop.service.limitedStrinds;

public class LimitedFloat {
    /**
     * Класс лимитированной по длине Float числа
     */

    private final float _min;
    private final float _max;
    private float _vale;

    /**
     * конструктор LimitedFloat
     * @param min минимальное значение
     * @param max максимальное значение
     */
    public LimitedFloat(float min, float max) throws Exception {
        _min = min;
        _max = max;
    }

    private void _check_vale(float vale) throws Exception {
        if (vale <= _min || vale >= _max) {
            throw new Exception("выход за огранмченый е приделы " + _min + " <=x<= " + _max);
        }

        _vale = vale;
    }

    /**
     * Установит новое значение
     * @param vale новое значение
     */
    public void setValue(float vale) throws Exception {
        _check_vale(vale);
    }

    /**
     * Вернет записанное значение
     * @return float число
     */
    public float getValue() {
        return _vale;
    }

    @Override
    public String toString() {
        return String.valueOf(_vale);
    }
}
