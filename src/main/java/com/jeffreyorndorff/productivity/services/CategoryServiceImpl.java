package com.jeffreyorndorff.productivity.services;

import com.jeffreyorndorff.productivity.models.Category;
import com.jeffreyorndorff.productivity.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService{
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
}
