package com.ablog.api;

import com.ablog.pojo.Category;
import com.ablog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
