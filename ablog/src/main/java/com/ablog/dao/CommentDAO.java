package com.ablog.dao;

import com.ablog.pojo.Article;
import com.ablog.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentDAO extends JpaRepository<Comment,Integer> {

    List<Comment> findCommentsByArticle(Article article);

    @Modifying
    @Transactional
    void deleteCommentByParentId(int parentId);

}
