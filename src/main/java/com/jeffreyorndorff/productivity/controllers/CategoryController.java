package com.jeffreyorndorff.productivity.controllers;

import com.jeffreyorndorff.productivity.models.SimpleCategory;
import com.jeffreyorndorff.productivity.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories", produces = "application/JSON")
    public ResponseEntity<?> listAllCategories() {
        List<SimpleCategory> categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping(value = "/category/{categoryId}", produces = "application/JSON")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId) {
        SimpleCategory category = categoryService.findCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping(value = "/category", consumes = "application/JSON")
    public ResponseEntity<?> addNewCategory(@Valid
                                            @RequestBody SimpleCategory newCategory) {
        newCategory.setCategoryid(0);
        newCategory = categoryService.save(newCategory);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCategoryURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{categoryid}")
                .buildAndExpand(newCategory.getCategoryid())
                .toUri();
        responseHeaders.setLocation(newCategoryURI);

        return new ResponseEntity<>(newCategory,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PatchMapping(value = "/category/{categoryId}", consumes = "application/JSON")
    public ResponseEntity<?> updateCategory(@RequestBody SimpleCategory updateCategory,
                                            @PathVariable long categoryId) {
        SimpleCategory updated = categoryService.update(updateCategory, categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/category/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable long categoryId) {
        categoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
