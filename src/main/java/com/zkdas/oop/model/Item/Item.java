package com.zkdas.oop.model.Item;

import com.zkdas.oop.model.Item.Events.*;
import com.zkdas.oop.service.IdGenerator;
import com.zkdas.oop.service.LimitedFields.LimitedFloat;
import com.zkdas.oop.service.LimitedFields.LimitedSting;
import com.zkdas.oop.service.Validators.ValueValidator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Item implements Cloneable, Comparable<Item> {
    /**
     * Класс модель данных Item
     */
    private static final IdGenerator _idGenerator = new IdGenerator();
    private final int _id; // целочисленное readonly-поле, хранящее уникальный номер товара.
    protected final LimitedSting _name = new LimitedSting(200); // строковое поле с названием товара, до 200 символов.
    private final LimitedSting _info = new LimitedSting(1_000); // строковое поле с описанием товара, до 1 000 символов.
    private final LimitedFloat _cost = new LimitedFloat(0, 100_000); // вещественное поле со стоимостью товара, от 0 до 100 000
    private Category _category;

    // списки обработчиков событий
    private List<IItemEventListener> NameChangedListeners = new ArrayList<>();
    private List<IItemEventListener> InfoChangedListeners = new ArrayList<>();
    private List<IItemEventListener> CostChangedListeners = new ArrayList<>();


    /**
     * Все поддерживаемые ивенты
     */
    public enum EventType{
        NameChanged, InfoChanged, CostChanged;
    }

    /**
     * Добавит слушатели для ивентов
     * @param type тип ивента
     * @param listener слушатель ивента
     */
    public void addEventListener(EventType type,IItemEventListener listener) {
        switch (type) {
            case NameChanged:
                NameChangedListeners.add(listener);
                break;
            case InfoChanged:
                InfoChangedListeners.add(listener);
                break;
            case CostChanged:
                CostChangedListeners.add(listener);
                break;
        }
    }

    /**
     * Сообщит всем обработчикам, что надо вызвать событие
     * @param event событие
     * @param listeners список событий
     */
    private void notifyListeners(ItemEvent event, List<IItemEventListener> listeners) {
        for (IItemEventListener listener : listeners) {
            listener.processEvent(event);
        }
    }

    @Override
    public int compareTo(@NotNull Item obj) {
        return Float.compare(this.getCost(), obj.getCost());
    }

    @Override
    public Item clone() {
        try {
            return new Item(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item other) {
            return other.getName().equals(getName()) && other.getInfo().equals(getInfo())
                    && other.getCost() == getCost() && other.getCategory() == getCategory();
        }
        return false;
    }

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
    public Item(Item item, boolean copyId) throws Exception {
        if (copyId) {
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
        notifyListeners(new NameChanged(this), NameChangedListeners);
    }


    public String getInfo() {
        return _info.toString();
    }

    public void setInfo(String info) throws Exception {
        ValueValidator.AssertStringOnLength(info, _info.getLength(), "info");

        _info.setData(info);
        notifyListeners(new InfoChanged(this), InfoChangedListeners);
    }

    public float getCost() {
        return _cost.getValue();
    }

    public void setCost(float cost) throws Exception {
        _cost.setValue(cost);
        notifyListeners(new CostChanged(this), CostChangedListeners);
    }

    public Category getCategory() {
        return _category;
    }

    public void setCategory(Category category) {
        _category = category;
    }
}