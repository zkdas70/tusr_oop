package com.zkdas.oop.service.DataTools;

import com.zkdas.oop.model.Item.Item;

import java.util.List;

public class DataTools {
    static <I extends Item, T extends List<I>> T SafeFilter(T in){
        return in;
    }
}
