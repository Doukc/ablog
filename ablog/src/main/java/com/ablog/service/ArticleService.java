package com.ablog.service;

import com.ablog.dao.ArticleDAO;
import com.ablog.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDAO articleDAO;

    public Article newArticle(Article article){

        return articleDAO.save(article);

    }

    public Page<Article> articleList(Pageable pageable) {


        return articleDAO.findAll(pageable);

    }

    public Article findArticleById(int id) {

        return articleDAO.findById(id).get();

    }
}
