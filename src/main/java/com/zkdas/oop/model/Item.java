package com.zkdas.oop.model;

import com.zkdas.oop.service.IdGenerator;
import com.zkdas.oop.service.limitedStrinds.LimitedFloat;
import com.zkdas.oop.service.limitedStrinds.LimitedSting;
import com.zkdas.oop.service.ValueValidator;

public class Item {
    /// <summary>
    ///  класс модель данных Item
    /// </summary>
    private static final IdGenerator _idGenerator = new IdGenerator();
    private final int _id;
    protected LimitedSting _name;
    private LimitedSting _info;
    private LimitedFloat _cost;

    public Item(String name, String info, float cost) throws Exception {
        _id = _idGenerator.get_next_id();

        setInfo(info);
        setName(name);
        setCost(cost);
    }

    public int getId() {
        return _id;
    }

    public String getName(){
        return _name.get_data();
    }
    public void setName(String name) throws Exception {
        ValueValidator.AssertStringOnLength(name, 200, "name");

        _name = new LimitedSting(200, name);
    }


    public String getInfo() {
        return _info.get_data();
    }

    public void setInfo(String info) throws Exception {
        ValueValidator.AssertStringOnLength(info, 1000, "info");

        _info = new LimitedSting(1000, info);
    }

    public float getCost() {
        return _cost.get_value();
    }

    public void setCost(float cost) throws Exception {
        _cost = new LimitedFloat(0, 100_000, cost);
    }
}