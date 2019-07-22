package com.ablog.dao;

import com.ablog.pojo.Article;
import com.ablog.pojo.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ArticleDAO extends JpaRepository<Article,Integer>, JpaSpecificationExecutor<Article> {

    List<Article> findArticlesByCategoryList(List<Category> categoryList, Sort sort);

}
