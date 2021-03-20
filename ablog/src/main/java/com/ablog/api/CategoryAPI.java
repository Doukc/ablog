package com.ablog.api;

import com.ablog.pojo.Category;
import com.ablog.service.CategoryService;
import com.ablog.service.LogService;
import com.ablog.utils.IPUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CategoryAPI {

    @Autowired
    CategoryService categoryService;
    @Autowired
    LogService logService;

    @PostMapping("/api/category/categories")
    public List categories(){

        return categoryService.categories();

    }

    @PostMapping("/api/category/categoryList")
    public List categories(@RequestBody Category category){

        return categoryService.categories(category);

    }

    @PostMapping("/api/category/save")
    public void save(HttpServletRequest request, @RequestBody Category category){

        categoryService.save(category);
        logService.log(IPUtil.getClientAddress(request),"其他管理-分类/标签","新增");

    }

    @PostMapping("/api/category/delete")
    public void delete(HttpServletRequest request, @RequestBody Category category){

        categoryService.delete(category);
        logService.log(IPUtil.getClientAddress(request),"其他管理-分类/标签","删除");

    }

    @GetMapping("/api/category/query")
    public List<Category> query(){

        List<Category> categories = categoryService.query();
        return categories;

    }

}
