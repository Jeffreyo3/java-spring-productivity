package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Category;
import com.jeffreyorndorff.productivity.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Category" +
                " id" +
                " " + id +
                " Not Found"));
    }
}
