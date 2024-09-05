package com.muhib.item_service.service;


import com.muhib.item_service.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> fetchItemList();

    Item saveItem(Item item);

    List<Item> fetchItemByName(String name);
}
