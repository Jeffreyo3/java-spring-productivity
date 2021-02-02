package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Item;
import com.jeffreyorndorff.productivity.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemrepo;

    @Override
    public Item findItemById(long id) {
        return itemrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Item id " + id + " Not Found"));
    }
}
