package com.muhib.item_service.controller;

import com.muhib.item_service.entity.Item;
import com.muhib.item_service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public List<Item> fetchItemList(){
        return itemService.fetchItemList();
    }

    @GetMapping("/item")
    public List<Item> fetchItemByName(@Validated @RequestParam String name){
        return itemService.fetchItemByName(name);
    }


//    @PostMapping("/item/add/{categoryId}")
//    public Item saveItem(@PathVariable Long categoryId, @RequestBody Item item){
//
//        return(itemService.saveItem(categoryId, item));
//    }

    @PostMapping("/item/add")
    public Item saveItem(
            @RequestBody Item item) {

        return itemService.saveItem(item);
    }

}
