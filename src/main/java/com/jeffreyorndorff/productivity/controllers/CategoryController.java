package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.models.Category;
import com.jeffreyorndorff.productivity.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories", produces = "application/JSON")
    public ResponseEntity<?> listAllCategories() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping(value = "/category/{categoryId}", produces = "application/JSON")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
