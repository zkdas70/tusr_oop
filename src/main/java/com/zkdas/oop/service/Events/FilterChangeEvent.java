package com.zkdas.oop.service.Events;

import java.util.EventObject;

public class FilterChangeEvent extends EventObject {
    private final String filterName;
    private final Object newValue;

    public FilterChangeEvent(Object source, String filterName, Object newValue) {
        super(source);
        this.filterName = filterName;
        this.newValue = newValue;
    }

    // Геттеры для получения информации о событии
    public String getFilterName() {
        return filterName;
    }

    public Object getNewValue() {
        return newValue;
    }
}