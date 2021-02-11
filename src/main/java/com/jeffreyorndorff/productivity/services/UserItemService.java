package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.helpermodels.SimpleItem;
import com.jeffreyorndorff.productivity.helpermodels.SimpleItemWithRecipes;
import com.jeffreyorndorff.productivity.helpermodels.SimpleUserItem;
import com.jeffreyorndorff.productivity.models.UserItem;

import java.util.List;

public interface UserItemService {
    List<SimpleUserItem> findAllItemsByUserid(long userId);

    SimpleUserItem convertUserItemToSimpleUserItem(UserItem userItem);
}
