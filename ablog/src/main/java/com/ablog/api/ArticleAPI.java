package com.ablog.api;

import com.ablog.pojo.Article;
import com.ablog.result.Result;
import com.ablog.result.ResultFactory;
import com.ablog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ArticleAPI {

    @Autowired
    ArticleService articleService;

    @PostMapping("/api/article")
    public Article article(@RequestBody Article article){

        return articleService.findArticleById(article.getId());

    }

    @PostMapping("/api/article/new")
    public Result newArticle(@RequestBody Article article){

        Article newArticle = articleService.newArticle(article);
        if (newArticle != null){
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("添加失败");

    }

    @GetMapping("/api/article/list/{currentPage}/{pageSize}")
    public Page<Article> articleList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){

        Pageable pageable = new PageRequest(currentPage - 1,pageSize,Sort.Direction.DESC,"createTime");
        Page<Article> articlePage = articleService.articleList(pageable);
        return articlePage;

    }

}
