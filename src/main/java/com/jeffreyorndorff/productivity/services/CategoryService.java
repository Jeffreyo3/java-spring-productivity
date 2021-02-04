package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(long id);

    List<Category> findAll();

    Category save(Category newCategory);

    Category update(Category updateCategory, long categoryId);

    void delete(long id);
}
