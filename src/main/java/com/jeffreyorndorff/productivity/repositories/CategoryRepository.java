package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByCategoryIgnoringCase(String category);
}
