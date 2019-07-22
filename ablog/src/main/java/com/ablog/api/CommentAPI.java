package com.ablog.api;

import com.ablog.pojo.Comment;
import com.ablog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentAPI {

    @Autowired
    CommentService commentService;

    @PostMapping("/api/comment/new/{articleId}")
    public void newComment(@RequestBody Comment comment, @PathVariable("articleId") int articleId){

        commentService.newComment(comment,articleId);

    }

    @GetMapping("/api/comment/list/{currentPage}/{pageSize}")
    public Page<Comment> commentList(@PathVariable("currentPage") int currentPage,@PathVariable("pageSize") int pageSize){

        Pageable pageable = new PageRequest(currentPage - 1,pageSize, Sort.Direction.DESC,"time");
        return commentService.commentList(pageable);

    }

    @GetMapping("/api/comment/comments/{articleId}")
    public List<Comment> comments(@PathVariable("articleId") int articleId){

        return commentService.comments(articleId);

    }

    @GetMapping("/api/comment/review/{commentId}")
    public void review(@PathVariable("commentId") int commentId){

        commentService.review(commentId);

    }

    @GetMapping("/api/comment/reply/{commentId}/{content}")
    public void reply(@PathVariable("commentId") int commentId,@PathVariable("content") String content){

        commentService.reply(commentId,content);

    }

    @GetMapping("/api/comment/delete/{commentId}")
    public void delete(@PathVariable("commentId") int commentId){

        commentService.delete(commentId);

    }

}
