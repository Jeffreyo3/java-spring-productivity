package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.helpermodels.SimpleItemRecipe;
import com.jeffreyorndorff.productivity.models.helpermodels.SimpleItemWithRecipes;
import com.jeffreyorndorff.productivity.models.models.Item;
import com.jeffreyorndorff.productivity.models.models.RecipeItem;
import com.jeffreyorndorff.productivity.models.models.UserItem;
import com.jeffreyorndorff.productivity.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service(value = "itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemrepo;

    @Override
    public Item findItemById(long id) {
        return itemrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Item id " + id + " Not Found"));
    }

    @Override
    public SimpleItemWithRecipes convertItemToSimpleItem(Item item) {
        Set<SimpleItemRecipe> recipeSet = new HashSet<>();

        for(RecipeItem ri : item.getRecipes()) {
            SimpleItemRecipe sir = new SimpleItemRecipe(
                    ri.getRecipe(),
                    ri.getQuantity(),
                    ri.getMeasurement()
            );

            recipeSet.add(sir);
        }

        return new SimpleItemWithRecipes(
                item.getItemid(),
                item.getItem(),
                item.getPrice(),
                item.getUrl(),
                recipeSet
        );
    }

    @Override
    public List<SimpleItemWithRecipes> findAll() {
        List<SimpleItemWithRecipes> list = new ArrayList<>();

        itemrepo.findAll().iterator().forEachRemaining(listItem ->
                list.add(convertItemToSimpleItem(listItem)));

        return list;
    }

    @Override
    public SimpleItemWithRecipes findSimpleItemById(long id) {
        Item item = findItemById(id);

        return convertItemToSimpleItem(item);
    }

    @Override
    public Item save(Item item) {
        Item newItem = new Item();

        newItem.setItem(item.getItem());
        newItem.setPrice(item.getPrice());
        newItem.setUrl(item.getUrl());


        newItem.getRecipes().clear();
        for (RecipeItem ri : newItem.getRecipes()) {
            RecipeItem newRecipeItem = new RecipeItem(
                    ri.getRecipe(),
                    newItem,
                    ri.getQuantity(),
                    ri.getMeasurement()
            );
            newItem.getRecipes().add(newRecipeItem);
        }

        newItem.getUsers().clear();
        for (UserItem ui : newItem.getUsers()) {
            UserItem newUserItem = new UserItem(
                    ui.getUser(),
                    newItem,
                    ui.getQuantity(),
                    ui.getNotes()
            );
            newItem.getUsers().add(newUserItem);
        }

        return itemrepo.save(newItem);
    }

    @Override
    public Item update(Item item, long itemId) {
        Item itemToUpdate = findItemById(itemId);

        if(item.getItem() != null) {
            itemToUpdate.setItem(item.getItem());
        }

        if (item.getPrice() != 0) {
            itemToUpdate.setPrice(item.getPrice());
        }

        if (item.getUrl() != null) {
            itemToUpdate.setUrl(item.getUrl());
        }

        if (item.getRecipes().size() != 0) {
            itemToUpdate.getRecipes().clear();
            for (RecipeItem ri : itemToUpdate.getRecipes()) {
                RecipeItem newRecipeItem = new RecipeItem(
                        ri.getRecipe(),
                        itemToUpdate,
                        ri.getQuantity(),
                        ri.getMeasurement()
                );
                itemToUpdate.getRecipes().add(newRecipeItem);
            }
        }

        if (item.getUsers().size() != 0) {
            itemToUpdate.getUsers().clear();
            for (UserItem ui : itemToUpdate.getUsers()) {
                UserItem newUserItem = new UserItem(
                        ui.getUser(),
                        itemToUpdate,
                        ui.getQuantity(),
                        ui.getNotes()
                );
                itemToUpdate.getUsers().add(newUserItem);
            }
        }

        return itemToUpdate;
    }

    @Transactional
    @Override
    public void delete(long itemId) {
        if (itemrepo.findById(itemId).isPresent()) {
            itemrepo.deleteById(itemId);
        } else {
            throw new EntityNotFoundException("Item with id " + itemId + " Not Found!");
        }
    }
}
