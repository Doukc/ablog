package com.ablog.service;

import com.ablog.dao.CategoryDAO;
import com.ablog.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
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

        categoryDAO.save(category);

    }
}
