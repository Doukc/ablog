package com.ablog.dao;

import com.ablog.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ArticleDAO extends JpaRepository<Article,Integer> {



}
