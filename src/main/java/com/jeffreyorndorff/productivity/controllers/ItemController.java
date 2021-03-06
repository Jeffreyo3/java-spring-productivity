package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleItemWithRecipes;
import com.jeffreyorndorff.productivity.models.helpermodels.SimpleUserItem;
import com.jeffreyorndorff.productivity.models.models.Item;
import com.jeffreyorndorff.productivity.models.models.User;
import com.jeffreyorndorff.productivity.services.ItemService;
import com.jeffreyorndorff.productivity.services.UserItemService;
import com.jeffreyorndorff.productivity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserItemService userItemService;

    @Autowired
    private UserService userService;

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
     * Create new item
     */
    @PostMapping(value = "/item", consumes = "application/JSON", produces = "application/JSON")
    public ResponseEntity<?> createItem(@Valid @RequestBody Item newItem) {
        newItem.setItemid(0);
        newItem = itemService.save(newItem);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newItemURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{itemid}")
                .buildAndExpand(newItem.getItemid())
                .toUri();
        responseHeaders.setLocation(newItemURI);

        return new ResponseEntity<>(newItem,
                responseHeaders,
                HttpStatus.CREATED);
    }

    /*
     * Update item by id
     */
    @PatchMapping(value = "/item/{itemId}", consumes = "application/JSON", produces =
            "application/JSON")
    public ResponseEntity<?> updateItem(@RequestBody Item item, @PathVariable long itemId) {
        itemService.update(item, itemId);
        return new ResponseEntity<>(item, HttpStatus.NO_CONTENT);
    }

    // TODO: Refactor once Security is implemented - only Admin should be able to use this endpoint
    /*
     * Delete item by id
     */
    @DeleteMapping(value = "/item/{itemId}", produces = "application/JSON")
    public ResponseEntity<?> deleteItemById(@PathVariable long itemId) {
        itemService.delete(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: Refactor once Security is implemented to grab userId from SecurityContext
    /*
     * Get all items by userid
     */
    @GetMapping(value = "/user/{userId}", produces = "application/JSON")
    public ResponseEntity<?> getItemsByUserId(@PathVariable long userId) {
        List<SimpleUserItem> itemList = userItemService.findAllItemsByUserid(userId);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }


    // TODO: Refactor once Security is implemented to grab userId from SecurityContext
    /*
     * Create new User to Item relationship
     */
    @PostMapping(value = "/user/{userId}", produces = "application/JSON", consumes = "application/JSON")
    public ResponseEntity<?> addUserItem(@PathVariable long userId,
                                         @Valid @RequestBody SimpleUserItem userItem) {
        SimpleUserItem newUserItem = userItemService.save(userItem, userId);
        return new ResponseEntity<>(newUserItem, HttpStatus.OK);
    }

    // TODO: Refactor once Security is implemented to grab userId from SecurityContext
    /*
     * Update UserItem
     */
    @PutMapping(value = "/user/{userId}", consumes = "application/JSON")
    public ResponseEntity<?> updateUserItem(@PathVariable long userId,
                                            @Valid @RequestBody SimpleUserItem userItem) {
        userItemService.update(userItem, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO: Refactor once Security is implemented to grab userId from SecurityContext
    /*
     * Remove UserItem
     */
    @DeleteMapping(value = "/user/item/{itemId}")
    public ResponseEntity<?> deleteUserItem(
//            @PathVariable long userId,
            @PathVariable long itemId) {
        User u = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()
        );
        userItemService.delete(u.getUserid(), itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
