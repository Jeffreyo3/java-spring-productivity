package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Item;

public interface ItemService {
    Item findItemById(long id);
}
