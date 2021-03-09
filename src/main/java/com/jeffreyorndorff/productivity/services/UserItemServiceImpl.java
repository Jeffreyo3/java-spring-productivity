package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.helpermodels.SimpleItem;
import com.jeffreyorndorff.productivity.helpermodels.SimpleUserItem;
import com.jeffreyorndorff.productivity.models.Item;
import com.jeffreyorndorff.productivity.models.User;
import com.jeffreyorndorff.productivity.models.UserItem;
import com.jeffreyorndorff.productivity.repositories.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userItemService")
public class UserItemServiceImpl implements UserItemService {
    @Autowired
    private UserItemRepository useritemrepo;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Override
    public SimpleUserItem convertUserItemToSimpleUserItem(UserItem userItem) {
        SimpleItem simpleItem = new SimpleItem(
                userItem.getItem().getItemid(),
                userItem.getItem().getItem(),
                userItem.getItem().getPrice(),
                userItem.getItem().getUrl()
        );

        return new SimpleUserItem(
                simpleItem,
                userItem.getQuantity(),
                userItem.isChecked(),
                userItem.getNotes());
    }

    @Override
    public List<SimpleUserItem> findAllItemsByUserid(long userId) {
        User user = userService.findUserById(userId);

        List<SimpleUserItem> list = new ArrayList<>();

        useritemrepo.findAllByUser(user).iterator().forEachRemaining(listItem ->
                list.add(convertUserItemToSimpleUserItem(listItem)));

        return list;
    }


    @Override
    public SimpleUserItem save(SimpleUserItem userItem, long userId) {
        Item item = itemService.findItemById(userItem.getItem().getItemid());

        User user = userService.findUserById(userId);

        UserItem newUserItem = new UserItem(user, item, userItem.getQuantity(), userItem.getNotes());

        newUserItem = useritemrepo.save(newUserItem);

        return new SimpleUserItem(
                new SimpleItem(newUserItem.getItem().getItemid(),
                        newUserItem.getItem().getItem(),
                        newUserItem.getItem().getPrice(),
                        newUserItem.getItem().getUrl()),
                newUserItem.getQuantity(),
                newUserItem.isChecked(),
                newUserItem.getNotes()
        );
    }

    @Override
    public void update(SimpleUserItem userItem, long userId) {
        // validate that item exists
        Item item = itemService.findItemById(userItem.getItem().getItemid());
        // validate that user exists
        User user = userService.findUserById(userId);

        /*
        * JPA query gives back a list, but since we're querying by
        * unique values, the list size should be 1 if it exists,
        * zero otherwise
        */
        List<UserItem> uiList = useritemrepo.findByUser_UseridAndItem_Itemid(
                user.getUserid(), item.getItemid()
        );

        if(uiList.size() == 0) {
            throw new EntityNotFoundException("Item id " + userItem.getItem().getItemid() + " Not" +
                    " Found on user " + userId);
        }

        UserItem updatedUserItem = new UserItem();

        updatedUserItem.setItem(item);
        updatedUserItem.setUser(user);
        if(userItem.getQuantity() >= 1) {
            updatedUserItem.setQuantity(userItem.getQuantity());
        } else {
            updatedUserItem.setQuantity(uiList.get(0).getQuantity());
        }
        updatedUserItem.setChecked(userItem.isChecked());
        updatedUserItem.setNotes(userItem.getNotes());


        useritemrepo.save(updatedUserItem);
    }
}
