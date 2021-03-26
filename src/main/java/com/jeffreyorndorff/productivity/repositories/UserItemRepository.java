package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.models.User;
import com.jeffreyorndorff.productivity.models.models.UserItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserItemRepository extends CrudRepository<UserItem, Long> {
    List<UserItem> findAllByUser(User user);

    List<UserItem> findByUser_UseridAndItem_Itemid(long userid, long itemid);

    void deleteByUser_UseridAndItem_Itemid(long userid, long itemid);
}
