package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.helpermodels.SimpleItemRecipe;
import com.jeffreyorndorff.productivity.helpermodels.SimpleItemWithRecipes;
import com.jeffreyorndorff.productivity.models.Item;
import com.jeffreyorndorff.productivity.models.RecipeItem;
import com.jeffreyorndorff.productivity.repositories.ItemRepository;
import com.jeffreyorndorff.productivity.repositories.UserItemRepository;
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

    @Autowired
    private UserItemRepository useritemrepo;

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
}
