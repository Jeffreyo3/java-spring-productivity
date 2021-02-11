package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.helpermodels.SimpleItem;
import com.jeffreyorndorff.productivity.helpermodels.SimpleItemWithRecipes;
import com.jeffreyorndorff.productivity.helpermodels.SimpleUserItem;
import com.jeffreyorndorff.productivity.services.ItemService;
import com.jeffreyorndorff.productivity.services.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserItemService userItemService;

    /*
    * List all items
    */
    @GetMapping(value = "/items", produces = "application/JSON")
    public ResponseEntity<?> listAllItems() {
        List<SimpleItemWithRecipes> itemList = itemService.findAll();
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    /*
    * Get item by item id
    */
    @GetMapping(value = "/item/{itemId}", produces = "application/JSON")
    public ResponseEntity<?> getItemById(@PathVariable long itemId) {
        SimpleItemWithRecipes item = itemService.findSimpleItemById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    /*
    * Get all items by userid
    */
    @GetMapping(value = "/user/{userId}", produces = "application/JSON")
    public ResponseEntity<?> getItemsByUserId(@PathVariable long userId) {
        List<SimpleUserItem> itemList = userItemService.findAllItemsByUserid(userId);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }
}
