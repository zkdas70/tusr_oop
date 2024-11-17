package com.zkdas.oop.service.DataTools;

import com.zkdas.oop.model.Item.Item;

import java.util.Comparator;
import java.util.List;

public class DataTools {
    static <I extends Item, T extends List<I>> T SafeFilter(T List){
        return List;
    }

    public static <I extends Item, T extends List<I>> T Sort(T list, Comparator<Item> c){
        list.sort(c);
        return list;
    }
}
