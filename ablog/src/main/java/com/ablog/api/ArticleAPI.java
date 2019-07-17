package com.ablog.api;

import com.ablog.pojo.Article;
import com.ablog.pojo.Category;
import com.ablog.result.Result;
import com.ablog.result.ResultFactory;
import com.ablog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleAPI {

    @Autowired
    ArticleService articleService;

    @PostMapping("/api/article")
    public Article article(@RequestBody Article article){

        return articleService.findArticleById(article.getId());

    }

    @PostMapping("/api/article/new/{value}/{tagValue}")
    public Result newArticle(@RequestBody Article article,@PathVariable("value") String[] value,@PathVariable("tagValue") String[] tagValue){

        Article newArticle = articleService.newArticle(article,value,tagValue);
        if (newArticle != null){
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("添加失败");

    }

    @PostMapping("/api/article/articles/{currentPage}/{pageSize}")
    public Page<Article> articleList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){

        Pageable pageable = new PageRequest(currentPage - 1,pageSize,Sort.Direction.DESC,"createTime");
        Page<Article> articlePage = articleService.articles(pageable);
        return articlePage;

    }
    @PostMapping("/api/article/list/{currentPage}/{pageSize}/{categoryId}")
    public Page<Article> articleList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize,@PathVariable("categoryId") int categoryId, @RequestBody Article article){

        Category category = new Category();
        if (categoryId != 0){
            category.setId(categoryId);
        }
        Pageable pageable = new PageRequest(currentPage - 1,pageSize,Sort.Direction.DESC,"createTime");
        Page<Article> articlePage = articleService.articleList(article,category,pageable);
        return articlePage;

    }

}
