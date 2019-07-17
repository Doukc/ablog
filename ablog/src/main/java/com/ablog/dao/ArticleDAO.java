package com.ablog.dao;

import com.ablog.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleDAO extends JpaRepository<Article,Integer>, JpaSpecificationExecutor<Article> {



}
