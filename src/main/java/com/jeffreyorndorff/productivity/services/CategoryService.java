package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.SimpleCategory;

import java.util.List;

public interface CategoryService {
    SimpleCategory findCategoryById(long id);

    List<SimpleCategory> findAll();

    SimpleCategory save(SimpleCategory newCategory);

    SimpleCategory update(SimpleCategory updateCategory, long categoryId);

    void delete(long id);
}
