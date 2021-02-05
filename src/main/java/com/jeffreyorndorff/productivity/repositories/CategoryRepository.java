package com.jeffreyorndorff.productivity.repositories;

import com.jeffreyorndorff.productivity.models.SimpleCategory;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<SimpleCategory, Long> {
    SimpleCategory findByCategoryIgnoringCase(String category);
}
