package com.ablog.api;

import com.ablog.pojo.Category;
import com.ablog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryAPI {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/api/category/categories")
    public List categories(){

        return categoryService.categories();

    }

    @PostMapping("/api/category/save")
    public void save(@RequestBody Category category){

        categoryService.save(category);

    }

    @PostMapping("/api/category/delete")
    public void delete(@RequestBody Category category){

        categoryService.delete(category);

    }

    @GetMapping("/api/category/query")
    public List<Category> query(){

        List<Category> categories = categoryService.query();
        for (Category category:
             categories) {
            System.out.println(category.toString());
        }
        return categories;

    }

}
