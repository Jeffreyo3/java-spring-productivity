package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.SimpleCategory;
import com.jeffreyorndorff.productivity.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryrepo;


    @Override
    public SimpleCategory findCategoryById(long id) {
        return categoryrepo.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Category" +
                        " id" +
                        " " + id +
                        " Not Found"));
    }

    @Override
    public List<SimpleCategory> findAll() {
        List<SimpleCategory> list = new ArrayList<>();

        categoryrepo.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public SimpleCategory findCategoryByName(String category) {
        return categoryrepo.findByCategoryIgnoringCase(category);
    }

    @Override
    public SimpleCategory save(SimpleCategory category) {
        SimpleCategory newCategory = new SimpleCategory();

        // keep from duplicating an existing category
        SimpleCategory existingCategory = findCategoryByName(category.getCategory());

        if (existingCategory != null) {
            throw new ValidationException("Category " + category.getCategory().toUpperCase() + " has " +
                    "already" +
                    " been created");
        }

        if (category.getCategoryid() != 0) {
            findCategoryById(category.getCategoryid());
            newCategory.setCategoryid(category.getCategoryid());
        }
        //

        newCategory.setCategory(category.getCategory().toUpperCase());

        return categoryrepo.save(newCategory);
    }

    @Override
    public SimpleCategory update(SimpleCategory category, long categoryId) {
        if (categoryrepo.findById(categoryId).isPresent()) {
            SimpleCategory categoryToUpdate = findCategoryById(categoryId);

            if (category.getCategory() != null) {
                categoryToUpdate.setCategory(category.getCategory());
            }

            // Tasks are not updated from Category

            return categoryrepo.save(categoryToUpdate);
        } else {
            throw new EntityNotFoundException("Category with id " + categoryId + " Not Found");
        }
    }

    @Transactional

    @Override
    public void delete(long id) {
        if (categoryrepo.findById(id).isPresent()) {
            categoryrepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Category with id " + id + " Not Found");
        }
    }
}
