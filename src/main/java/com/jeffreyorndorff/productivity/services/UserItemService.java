package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleUserItem;
import com.jeffreyorndorff.productivity.models.models.UserItem;

import java.util.List;

public interface UserItemService {
    List<SimpleUserItem> findAllItemsByUserid(long userId);

    UserItem findByUseridAndItemid(long userId, long itemId);

    SimpleUserItem convertUserItemToSimpleUserItem(UserItem userItem);

    SimpleUserItem save(SimpleUserItem userItem, long userId);

    void update(SimpleUserItem userItem, long userId);

    void delete(long userId, long itemId);
}
