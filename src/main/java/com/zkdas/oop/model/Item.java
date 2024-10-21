package com.zkdas.oop.model;

import com.zkdas.oop.service.IdGenerator;
import com.zkdas.oop.service.limitedStrinds.LimitedFloat;
import com.zkdas.oop.service.limitedStrinds.LimitedSting;
import com.zkdas.oop.service.Validators.ValueValidator;

public class Item {
    /**
     * Класс модель данных Item
     */
    private static final IdGenerator _idGenerator = new IdGenerator();
    private final int _id; // целочисленное readonly-поле, хранящее уникальный номер товара.
    protected final LimitedSting _name = new LimitedSting(200); // строковое поле с названием товара, до 200 символов.
    private final LimitedSting _info = new LimitedSting(1_000); // строковое поле с описанием товара, до 1 000 символов.
    private final LimitedFloat _cost = new LimitedFloat(0, 100_000); // вещественное поле со стоимостью товара, от 0 до 100 000
    private Category _category;

    /**
     * конструктор Item
     * @param name String названием товара, до 200 символов
     * @param info String описанием товара, до 1 000 символов
     * @param cost float стоимость товара, от 0 до 100 000
     * @param category енум Category
     */
    public Item(String name, String info, float cost, Category category) throws Exception {
        _id = _idGenerator.get_next_id();

        setInfo(info);
        setName(name);
        setCost(cost);

        setCategory(category);
    }

    /**
     * Копирующий конструктор Item
     * @param item объект, что надо скопировать
     * @param copyId True скопирует id, False нет
     */
    public Item(Item item,  boolean copyId) throws Exception {
        if (copyId){
            _id = item._id;
        } else {
            _id = _idGenerator.get_next_id();
        }
        setInfo(item.getInfo());
        setName(item.getName());
        setCost(item.getCost());
        setCategory(item.getCategory());
    }
    /**
     * Копирующий конструктор Item, копирует id
     * @param item объект, что надо скопировать
     */
    public Item(Item item) throws Exception {
        this(item, true);
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name.toString();
    }

    public void setName(String name) throws Exception {
        ValueValidator.AssertStringOnLength(name, _name.getLength(), "name");

        _name.setData(name);
    }


    public String getInfo() {
        return _info.toString();
    }

    public void setInfo(String info) throws Exception {
        ValueValidator.AssertStringOnLength(info, _info.getLength(), "info");

        _info.setData(info);
    }

    public float getCost() {
        return _cost.getValue();
    }

    public void setCost(float cost) throws Exception {
        _cost.setValue(cost);
    }

    public Category getCategory() {
        return _category;
    }
    public void setCategory(Category category) {
        _category = category;
    }
}