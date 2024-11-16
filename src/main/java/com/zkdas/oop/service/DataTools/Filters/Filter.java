package com.zkdas.oop.service.DataTools.Filters;

import com.zkdas.oop.model.Item.Item;

import java.util.List;

@FunctionalInterface
public interface Filter <T extends Item> {
      List<T> get_list(List<T> in_list);
}
