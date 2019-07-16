package com.ablog.service;

import com.ablog.dao.ArticleDAO;
import com.ablog.dao.CategoryDAO;
import com.ablog.pojo.Article;
import com.ablog.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    CategoryDAO categoryDAO;

    public Article newArticle(Article article, String[] value, String[] tagValue){

        List<Category> categories = new ArrayList<Category>();
        Category category = null;
        for (String cid :
                value) {
            category = categoryDAO.getOne(Integer.valueOf(cid));
            categories.add(category);
        }
        for (String cid :
                tagValue) {
            category = categoryDAO.getOne(Integer.valueOf(cid));
            categories.add(category);
        }
        article.setCategoryList(categories);
        return articleDAO.save(article);

    }

    public Page<Article> articleList(Pageable pageable) {

        return articleDAO.findAll(pageable);

    }

    public Article findArticleById(int id) {

        return articleDAO.findById(id).get();

    }
}
