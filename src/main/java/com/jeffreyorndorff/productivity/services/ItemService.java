package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.helpermodels.SimpleItem;
import com.jeffreyorndorff.productivity.helpermodels.SimpleItemWithRecipes;
import com.jeffreyorndorff.productivity.models.Item;

import java.util.List;

public interface ItemService {
    Item findItemById(long id);

    SimpleItemWithRecipes convertItemToSimpleItem(Item item);

    SimpleItemWithRecipes findSimpleItemById(long id);

    List<SimpleItemWithRecipes> findAll();

    Item save(Item item);

    Item update(Item item, long itemId);

    void delete(long itemId);
}
