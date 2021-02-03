package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Category;

public interface CategoryService {
    Category findCategoryById(long id);
}
