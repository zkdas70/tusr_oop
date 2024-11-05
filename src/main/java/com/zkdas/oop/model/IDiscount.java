package com.zkdas.oop.model;

import java.util.List;

public interface IDiscount {
    public String getInfo();

    public <I extends Item, T extends List<I>> double calculate(T items);

    public <I extends Item, T extends List<I>> double Apply(T items);

    public <I extends Item, T extends List<I>> void Update(T items);
}
