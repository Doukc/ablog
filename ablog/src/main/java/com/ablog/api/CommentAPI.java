package com.ablog.api;

import com.ablog.pojo.Comment;
import com.ablog.service.CommentService;
import com.ablog.service.LogService;
import com.ablog.utils.IPUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CommentAPI {

    @Autowired
    CommentService commentService;
    @Autowired
    LogService logService;

    @PostMapping("/api/comment/new/{articleId}")
    public void newComment(HttpServletRequest request, @RequestBody Comment comment, @PathVariable("articleId") int articleId){

        comment.setCommentatorIP(IPUtil.getClientAddress(request));
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
    public void review(HttpServletRequest request,@PathVariable("commentId") int commentId){

        commentService.review(commentId);
        logService.log(IPUtil.getClientAddress(request),"其他管理-评论管理","审核");

    }

    @GetMapping("/api/comment/reply/{commentId}/{content}")
    public void reply(HttpServletRequest request,@PathVariable("commentId") int commentId,@PathVariable("content") String content){

        commentService.reply(commentId,content);
        logService.log(IPUtil.getClientAddress(request),"其他管理-评论管理","回复");

    }

    @GetMapping("/api/comment/delete/{commentId}")
    public void delete(HttpServletRequest request,@PathVariable("commentId") int commentId){

        commentService.delete(commentId);
        logService.log(IPUtil.getClientAddress(request),"其他管理-评论管理","删除评论");

    }

    @GetMapping("/api/comment/allComment")
    public List<Comment> allComment(){

        return commentService.allComment();


    }

}
