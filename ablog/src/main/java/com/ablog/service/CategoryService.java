package com.ablog.service;

import com.ablog.dao.CategoryDAO;
import com.ablog.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    public List<Object[]> categories(){

        return categoryDAO.selectCategories();

    }

    public void save(Category category) {

        categoryDAO.saveAndFlush(category);

    }

    public void delete(Category category) {

        System.out.println(category.toString());
        categoryDAO.delete(category);

    }

    public List<Category> query() {

//        Category category = new Category();
//        category.setCategoryName(query);
//        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("categoryName",ExampleMatcher.GenericPropertyMatchers.contains());
//        Example<Category> example = Example.of(category,exampleMatcher);
        return categoryDAO.findAll();

    }

    public List categories(Category category) {

        return categoryDAO.findByCategoryType(category.getCategoryType());

    }
}
