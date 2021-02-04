package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(long id);

    List<Category> findAll();
}
