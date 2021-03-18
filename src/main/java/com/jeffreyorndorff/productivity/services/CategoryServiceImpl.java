package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.models.Category;
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
    public Category findCategoryById(long id) {
        return categoryrepo.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Category" +
                        " id" +
                        " " + id +
                        " Not Found"));
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();

        categoryrepo.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Category findCategoryByName(String category) {
        return categoryrepo.findByCategoryIgnoringCase(category);
    }

    @Override
    public Category save(Category category) {
        Category newCategory = new Category();

        // keep from duplicating an existing category
        Category existingCategory = findCategoryByName(category.getCategory());

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
    public Category update(Category category, long categoryId) {
        if (categoryrepo.findById(categoryId).isPresent()) {
            Category categoryToUpdate = findCategoryById(categoryId);

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
