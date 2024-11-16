package com.zkdas.oop.controller.modelForController;

import com.zkdas.oop.model.Item.Item;

import java.util.List;

public interface Filter <T extends Item> {
      List<T> get_list(List<T> in_list);
}
