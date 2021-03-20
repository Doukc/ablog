package com.ablog.service;

import com.ablog.dao.ArticleDAO;
import com.ablog.dao.CommentDAO;
import com.ablog.pojo.Article;
import com.ablog.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;
    @Autowired
    ArticleDAO articleDAO;

    public void newComment(Comment comment,int articleId){

        Article article = articleDAO.getOne(articleId);
        comment.setArticle(article);
        comment.setTime(new Date());
        comment.setState(0);
        commentDAO.save(comment);

    }

    public Page<Comment> commentList(Pageable pageable) {

        return commentDAO.findAll(pageable);

    }

    public List<Comment> comments(int articleId) {

        Article article = articleDAO.getOne(articleId);
        return commentDAO.findCommentsByArticle(article);

    }

    public void review(int commentId) {

        Comment comment = commentDAO.getOne(commentId);
        comment.setState(1);
        commentDAO.saveAndFlush(comment);

    }

    public void reply(int commentId, String content) {

        Comment parentComment = commentDAO.getOne(commentId);

        Comment comment = new Comment();
        comment.setParentId(commentId);
        comment.setState(2);
        comment.setContent(content);
        comment.setTime(new Date());
        comment.setArticle(parentComment.getArticle());
        commentDAO.save(comment);

    }

    public void delete(int commentId) {

        commentDAO.deleteById(commentId);
        commentDAO.deleteCommentByParentId(commentId);

    }

    public List<Comment> allComment() {

        return commentDAO.findAll();

    }
}
